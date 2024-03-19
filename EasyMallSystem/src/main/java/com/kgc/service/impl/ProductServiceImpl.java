package com.kgc.service.impl;

import com.kgc.dao.ProductDao;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.Product;
import com.kgc.entity.Category;
import com.kgc.entity.Product;
import com.kgc.service.CategoryService;
import com.kgc.service.ProductService;
import com.kgc.utils.ElsearchUtil;
import org.apache.log4j.Logger;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 15:50
 **/
@Service
public class ProductServiceImpl implements ProductService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ElsearchUtil elsearchUtil;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

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
    public Message getProductListByPage(Page page, Product product) {
        logger.info("ProductServiceImpl getProductListByPage is start.... id");
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (product != null && !product.getName().equals("")) {
            queryBuilder.must(QueryBuilders.matchQuery("pname", product.getName()));
        }


        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("pname");
        highlightBuilder.preTags("<font style='color:red'>");
        highlightBuilder.postTags("</font>");
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        nativeSearchQueryBuilder.withPageable(PageRequest.of(page.getCurrentPageNo() - 1, page.getPageSize()));
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price"));
        nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);

        ArrayList<Product> productList = new ArrayList<>();
        SearchHits<? extends Product> searchHits = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), product.getClass());
        for (
                SearchHit<? extends Product> searchHit : searchHits) {
            Product productTemp = searchHit.getContent();
            List<String> pname = searchHit.getHighlightField("pname");
            if (pname.size() > 0) {
                productTemp.setName(pname.get(0));
            }
            productList.add(productTemp);
        }

        long totalHits = searchHits.getTotalHits();
        page.setTotalCount(new Long(totalHits).intValue());
        page.setData(productList);
        return Message.success(page);
    }

    @Override
    public Message searchProductByCategoryName(String categoryName) {
        logger.info("ProductServiceImpl searchProductByCategoryName is start....categoryName:"+categoryName);
        List<Product> productList = new ArrayList<>();
        logger.info("categoryService getThreeCategoryList is start....categoryName:"+categoryName);
        List<Category> categoryList = categoryService.getThreeCategoryList(categoryName);
        logger.info("categoryService getThreeCategoryList is start....categoryName:"+categoryName+"result:"+categoryList);
        for(Category category:categoryList){
            logger.info("productDao getProductBythreeCategoryName is start....categoryName:"+category);
            List<Product> productBythreeCategoryName = productDao.getProductBythreeCategoryName(category.getName());
            for(Product product:productBythreeCategoryName){
                try {
                    String picPath = URLEncoder.encode(product.getFilePath(), "utf-8");
                    product.setFilePath(picPath);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                productList.add(product);
            }
            logger.info("productDao getProductBythreeCategoryName is start....categoryName:"+category+"result:"+productBythreeCategoryName);
        }
        return Message.success(productList);
    }

    @Override
    public Message searchHotProduct() {
        logger.info("ProductServiceImpl searchProductByCategoryName is start....");
        List<Product> productList = productDao.getProductByOrder();
        List<Product> products = new ArrayList<>();
        for (Product product: productList){
            try {
                String picPath = URLEncoder.encode(product.getFilePath(),"utf-8");
                product.setFilePath(picPath);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            products.add(product);
        }
        logger.info("productDao getProductByOrder is start....");
        return Message.success(products);
    }

    @Override
    public Message getProductById(int id) {
        logger.info("ProductServiceImpl getProductById is start....id:"+id);
        Product product = productDao.getProductById(id);
        try {
            String picPath = null;
            picPath = URLEncoder.encode(product.getFilePath(),"utf-8");
            product.setFilePath(picPath);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        if(product!=null){
            return Message.success(product);
        }
        return Message.error("没有该产品！");
    }
}
