package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.Product;

import com.kgc.entity.Product;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    /**
     *通过一级类找到下面的所有商品
     */
    public Message searchProductByCategoryName(String categoryName);

    /**
     * 查找四条热门产品（销量）
     * @return
     */
    public Message searchHotProduct();

    /**
     * 通过id找产品
     * @return
     */
    public Message getProductById(int id);

    /**
     *通过pid查找推荐搭配的三个商品
     * @return
     */
    public Message getSameFriCategoryProduct(int id);


    public Message getProductListAll();

    public Message addProductListByALL();
    public Message getProductListByPage(Page page, Product product,int minPrice,int MaxPrice,int isSort);

    /**
     * 分页显示产品
     */
    public Message getProductPageList(int currentPageNo,int pageSize,String productName,int brandId);

    /**
     * 删除商品
     */
    public Message delProductById(int id);

    /**
     * 添加商品
     */
    public Message addProduct(Product product, @RequestParam(value = "picPath") MultipartFile picPath, Model model);


}
