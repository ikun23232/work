package com.kgc.dao;

import com.kgc.entity.Page;
import com.kgc.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    /**
     * 查找所有商品
     * @return
     */
    public List<Product> getProductList();

    /**
     * 通过三级类名查找产品
     */
    public List<Product> getProductBythreeCategoryName(String categoryName);

    /**
     * 通过productId找到file文件
     */
    public String getfileByproductId(int id);

    /**
     * 通过id找product
     */
    public Product getProductById(int id);

    /**
     * 通过销量查找product
     */
    public List<Product> getProductByOrder();

    /**
     * 根据产品id查看库存
     * @return
     */
   public int getProductStockById(int id);


    public int updateProductStockById(@Param("delStock")int delStock,@Param("id") int id);

    /**
     * 通过pid查找相同第一级别的商品
     */
    public List<Product> getSameFristCategoryProductByPid(int pid);

    /**
     * 通过pid删除商品（逻辑）
     */
    public int delProductById(int id);

    /**
     * 查找分页所有商品通过pid、brandid
     * @return
     */
    public List<Product> getProductPageList(@Param("productName") String productName,@Param("brandId") int brandId);

    /**
     * 添加product
     * @return
     */
    public int addProduct(Product product);

    /**
     * 修改product
     * @return
     */
    public int updateProduct(Product product);

    /**
     * 修改file
     * @return
     */
    public int updatefile(Product product);
    /**
     * 添加file
     * @return
     */
    public int addfile(Product product);

    /**
     * 补全文件表
     * @return
     */
    public int upfile(Product product);
    /**
     * 补全产品表
     * @return
     */
    public int upProduct(Product product);
    /**
     * 通过pname查找是否有同名
     */
    public Product checkSameName(String name);

}
