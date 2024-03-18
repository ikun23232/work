package com.kgc.service.impl;

import com.kgc.dao.ProductDao;
import com.kgc.entity.Category;
import com.kgc.entity.Product;
import com.kgc.service.CategoryService;
import com.kgc.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CategoryService categoryService;


    @Override
    public List<Product> searchProductByCategoryName(String categoryName) {
        logger.info("ProductServiceImpl searchProductByCategoryName is start....categoryName:"+categoryName);
        List<Product> productList = new ArrayList<>();
        logger.info("categoryService getThreeCategoryList is start....categoryName:"+categoryName);
        List<Category> categoryList = categoryService.getThreeCategoryList(categoryName);
        logger.info("categoryService getThreeCategoryList is start....categoryName:"+categoryName+"result:"+categoryList);
        for(Category category:categoryList){
            logger.info("productDao getProductBythreeCategoryName is start....categoryName:"+category);
            List<Product> productBythreeCategoryName = productDao.getProductBythreeCategoryName(category.getName());
            for(Product product:productBythreeCategoryName){
                productList.add(product);
            }
            logger.info("productDao getProductBythreeCategoryName is start....categoryName:"+category+"result:"+productBythreeCategoryName);
        }
        return productList;
    }
}
