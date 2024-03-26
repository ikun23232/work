package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CategoryDao;
import com.kgc.dao.ConcernDao;
import com.kgc.dao.ProductDao;
import com.kgc.entity.*;
import com.kgc.entity.Product;
import com.kgc.service.CategoryService;
import com.kgc.service.ConcernService;
import com.kgc.service.ProductService;
import com.kgc.utils.ElsearchUtil;
import com.kgc.utils.ReplayUtil;
import com.kgc.utils.UserSessionUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.EncodingException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 15:50
 **/
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ElsearchUtil elsearchUtil;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ConcernDao concernDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private ReplayUtil replayUtil;

    /**
     * 查看所有的product
     *
     * @return
     */
    @Override
    public Message getProductListAll() {
        logger.info("ProductServiceImpl getProductListAll is start......");
        List<Product> productList = productDao.getProductList();
        logger.info("ProductServiceImpl getProductListAll is start......productList" + productList);
        return Message.success(productList);
    }

    @Override
    public Message addProductListByALL() {
        logger.info("ProductServiceImpl addProductListByALL is start......");
        List<Product> productList = productDao.getProductList();
        Iterable<Product> productIterable = elsearchUtil.saveAll(productList);
        logger.info("ProductServiceImpl addProductListByALL is start......productIterable" + productIterable);

        if (productIterable != null && productIterable.iterator().hasNext()) {
            return Message.success("添加成功");
        }
        return Message.error("同步失败");
    }

    /**
     * es条件查询
     *
     * @param page
     * @param product
     * @return
     */
    @Override
    public Message getConcernListByPage(Page page, Product product, int minPrice, int maxPrice) {
        logger.info("ProductServiceImpl getProductListByPage is start.... id");
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.filter(QueryBuilders.rangeQuery("price").gte(minPrice).lte(maxPrice));
        if (product != null && !product.getName().equals("")) {
            queryBuilder.must(QueryBuilders.matchQuery("name", product.getName()));
        }

        if (product != null && !product.getBrandName().equals("")) {
            queryBuilder.must(QueryBuilders.matchQuery("brandName", product.getBrandName()));
        }

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<font style='color:red'>");
        highlightBuilder.postTags("</font>");
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        nativeSearchQueryBuilder.withPageable(PageRequest.of(page.getCurrentPageNo() - 1, page.getPageSize()));
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price"));
        nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);

        ArrayList<Product> productList = new ArrayList<>();
        //拿到登录的userid
        int userId = 22;
        List<Concern> concernPageList = concernDao.getConcernPageListES(userId);

        SearchHits<? extends Product> searchHits = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), product.getClass());
        for (
                SearchHit<? extends Product> searchHit : searchHits) {
            Product productTemp = searchHit.getContent();
            //遍历拿到concern
            for (Concern concern : concernPageList) {
                if (concern.getProductId()==productTemp.getId()){
                    productTemp.setConcernFalg(true);
                }
            }
            List<String> pname = searchHit.getHighlightField("name");
            if (pname.size() > 0) {
                productTemp.setName(pname.get(0));
            }
            try {
                productTemp.setFilePath(URLEncoder.encode(productTemp.getFilePath(), "utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            productList.add(productTemp);
        }

        long totalHits = searchHits.getTotalHits();
        page.setTotalCount(new Long(totalHits).intValue());
        page.setData(productList);
        return Message.success(page);
    }


    /**
     * es条件查询
     *
     * @param page
     * @param product
     * @return
     */
    public Message getProductListByPage(Page page, Product product, int minPrice, int maxPrice,int isSort) {
        logger.info("ProductServiceImpl getProductListByPage is start.... id");
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.filter(QueryBuilders.rangeQuery("price").gte(minPrice).lte(maxPrice));
        if (product != null && !product.getName().equals("")) {
            queryBuilder.must(QueryBuilders.matchQuery("name", product.getName()));
        }

        if (product != null && !product.getBrandName().equals("")) {
            queryBuilder.must(QueryBuilders.matchQuery("brandName", product.getBrandName()));
        }
        //新加的产品
        List<Integer> categoryIds = new ArrayList<>();
// 将三级分类的 ID 添加到 categoryIds 列表中




        if (product != null && product.getCategoryId()!=null) {
            int typeById = categoryDao.getTypeById(product.getCategoryId());
            if (typeById==1){
                List<Category> threeCategoryBycategoryId = categoryDao.getThreeCategoryBycategoryId(product.getCategoryId());
                if (threeCategoryBycategoryId==null&&threeCategoryBycategoryId.size()>0){
                    categoryIds.add(product.getCategoryId());
                }
                for (Category category : threeCategoryBycategoryId) {
                    categoryIds.add(category.getId());
                }
            }else if (typeById==2){
                List<Category> threeCategoryBycategoryIdByTwo = categoryDao.getThreeCategoryBycategoryIdByTwo(product.getCategoryId());
                if (threeCategoryBycategoryIdByTwo==null&&threeCategoryBycategoryIdByTwo.size()>0){
                    categoryIds.add(product.getCategoryId());
                }
                for (Category category : threeCategoryBycategoryIdByTwo) {
                    categoryIds.add(category.getId());
                }
            }else {
                categoryIds.add(product.getCategoryId());
            }
            if (!categoryIds.isEmpty()) {
                queryBuilder.must(QueryBuilders.termsQuery("categoryId", categoryIds));
            }else {
                queryBuilder.must(QueryBuilders.matchQuery("categoryId", product.getCategoryId()));

            }
        }
        //
        if (categoryIds.isEmpty()&&product.getCategoryId()!=null) {
            queryBuilder.must(QueryBuilders.termsQuery("categoryId", categoryIds));

        }


        //
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<font style='color:red'>");
        highlightBuilder.postTags("</font>");
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        nativeSearchQueryBuilder.withPageable(PageRequest.of(page.getCurrentPageNo() - 1, page.getPageSize()));
        nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);

//默认降序
        if (isSort==0){
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));

        }else {
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }

        //先拿出收藏表里面的
        ArrayList<Product> productList = new ArrayList<>();
        SearchHits<? extends Product> searchHits = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), product.getClass());
        for (SearchHit<? extends Product> searchHit : searchHits) {
            Product productTemp = searchHit.getContent();


            List<String> pname = searchHit.getHighlightField("name");
            if (pname.size() > 0) {
                productTemp.setName(pname.get(0));
            }
            try {
                productTemp.setFilePath(URLEncoder.encode(productTemp.getFilePath(), "utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            productList.add(productTemp);
        }

        long totalHits = searchHits.getTotalHits();
        page.setTotalCount(new Long(totalHits).intValue());
        page.setData(productList);
        return Message.success(page);
    }

    @Override
    public Message getProductPageList(int currentPageNo, int pageSize, String productName, int brandId) {
        logger.info("ProductServiceImpl getProductPageList is start....");
        PageHelper.startPage(currentPageNo, pageSize);
        List<Product> productList = productDao.getProductPageList(productName, brandId);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        List<Product> products = new ArrayList<>();
        for (Product product : productList) {
            replayUtil.encodingFilePath(product);
            products.add(product);
        }
        pageInfo.setList(products);
        return Message.success(pageInfo);
    }

    @Override
    public Message delProductById(int id) {
        logger.info("ProductServiceImpl delProductById is start....id:" + id);
        int i = productDao.delProductById(id);
        if (i > 0) {
            return Message.success();
        } else {
            return Message.error();
        }
    }

    @Override
    public Message addProduct(Product product, @RequestParam(value = "picPath") MultipartFile picPath, Model model) {
        logger.info("ProductServiceImpl addProduct is start....product:" + product);
        String extsion = null;
        String Path = null;
        if (picPath != null) {
            if (!picPath.isEmpty()) {
                String originalFilename = picPath.getOriginalFilename();
                extsion = FilenameUtils.getExtension(originalFilename);
                Path = "C:\\IMG" + File.separator + UUID.randomUUID() + "." + extsion;
//                Path = "E:\\MyFile\\filepath" + File.separator + UUID.randomUUID() + "." + extsion;
            }

//            if (!extsion.equalsIgnoreCase("jpg") && !extsion.equalsIgnoreCase("png")) {
//                model.addAttribute("error", "文件格式有误只能上传jpg或者png");
//                return "regsiter";
//            }
//            if (mFile.getSize() > 5 * 1024 * 1024) {
//                model.addAttribute("error", "文件大小不能超过5MB");
//                return "regsiter";
//            }

            try {
                picPath.transferTo(new File(Path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        product.setFilePath(Path);
        int userId = UserSessionUtil.getUserId();
        product.setId(userId);
        int count = productDao.addfile(product);
        int i = productDao.addProduct(product);
        return Message.success();
        }

    @Override
    public Message checkSameName(String name) {
        logger.info("ProductServiceImpl checkSameName is start....name:" + name);
        Product product = productDao.checkSameName(name);
        if(product!=null){
            return Message.error();
        }else {
            return Message.success();
        }
    }

    @Override
    public Message checkSameNameUpdate(String name, int id) {
        logger.info("ProductServiceImpl checkSameName is start....name:" + name+"id:"+id);
        Product product = productDao.checkSameName(name);
        Product productMy = productDao.getProductById(id);
        if(product!=null){
            if(productMy.getName().equals(product.getName())){
                return Message.success();
            }
            return Message.error();
        }else {
            return Message.success();
        }
    }

    @Override
    public Message updateProduct(Product product, MultipartFile picPath, Model model) {
        logger.info("ProductServiceImpl updateProduct is start....product:" + product);
        String extsion = null;
        String Path = null;
        if (picPath != null) {
            if (!picPath.isEmpty()) {
                String originalFilename = picPath.getOriginalFilename();
                extsion = FilenameUtils.getExtension(originalFilename);
//                Path = "E:\\MyFile\\filepath" + File.separator + UUID.randomUUID() + "." + extsion;
                Path = "C:\\IMG" + File.separator + UUID.randomUUID() + "." + extsion;
//                Path = "E:\\MyFile\\filepath" + File.separator + UUID.randomUUID() + "." + extsion;
            }

//            if (!extsion.equalsIgnoreCase("jpg") && !extsion.equalsIgnoreCase("png")) {
//                model.addAttribute("error", "文件格式有误只能上传jpg或者png");
//                return "regsiter";
//            }
//            if (mFile.getSize() > 5 * 1024 * 1024) {
//                model.addAttribute("error", "文件大小不能超过5MB");
//                return "regsiter";
//            }

            try {
                picPath.transferTo(new File(Path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Product productfileId = productDao.getProductById(product.getId());
        product.setFilePath(Path);
        product.setFileId(productfileId.getFileId());
        int count = productDao.updateProduct(product);
        int i = productDao.updatefile(product);
        return Message.success();
    }


    @Override
    public Message searchProductByCategoryName(String categoryName) {
        logger.info("ProductServiceImpl searchProductByCategoryName is start....categoryName:" + categoryName);
        List<Product> productList = new ArrayList<>();
        logger.info("categoryService getThreeCategoryList is start....categoryName:" + categoryName);
        List<Category> categoryList = categoryService.getThreeCategoryList(categoryName);
        logger.info("categoryService getThreeCategoryList is start....categoryName:" + categoryName + "result:" + categoryList);
        for (Category category : categoryList) {
            logger.info("productDao getProductBythreeCategoryName is start....categoryName:" + category);
            List<Product> productBythreeCategoryName = productDao.getProductBythreeCategoryName(category.getName());
            for (Product product : productBythreeCategoryName) {
                product = replayUtil.encodingFilePath(product);
                productList.add(product);
            }
            logger.info("productDao getProductBythreeCategoryName is start....categoryName:" + category + "result:" + productBythreeCategoryName);
        }
        return Message.success(productList);
    }

    @Override
    public Message searchHotProduct() {
        logger.info("ProductServiceImpl searchProductByCategoryName is start....");
        List<Product> productList = productDao.getProductByOrder();
        List<Product> products = new ArrayList<>();
        for (Product product : productList) {
            product = replayUtil.encodingFilePath(product);
            products.add(product);
        }
        logger.info("productDao getProductByOrder is start....");
        return Message.success(products);
    }

    @Override
    public Message getProductById(int id) {
        logger.info("ProductServiceImpl getProductById is start....id:" + id);
        Product product = productDao.getProductById(id);
        if (product != null) {
            product = replayUtil.encodingFilePath(product);
            return Message.success(product);
        }
        return Message.error("没有该产品！");
    }

    @Override
    public Message getSameFriCategoryProduct(int id) {
        logger.info("ProductServiceImpl getSameFriCategoryProduct is start....id:" + id);
        List<Product> products = new ArrayList<>();
        List<Product> productList = productDao.getSameFristCategoryProductByPid(id);
        for (Product product : productList) {
            product = replayUtil.encodingFilePath(product);
            products.add(product);
        }
        return Message.success(products);
    }

}
