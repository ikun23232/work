package com.kgc.dao;


import com.kgc.entity.Collections;
import com.kgc.entity.Product;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface CollectionDao {
    public List<Product> getConnectionById(int id);


    public int delProductInCarById(int productId);


    public int UpdateProductInCarById1(@Param("userId") int userId,@Param("productId") int productId, @Param("quantity") int quantity);



    public int UpdateProductInCarById(@Param("productId") int productId, @Param("quantity") int quantity,@Param("userId")int userId);


    public int addProductInCarById(@Param("productId") int productId, @Param("quantity") int quantity,@Param("userId")int userId);

    public List<Product>  getProductAndQuantityById(@Param("ids")int[] id,@Param("userId")int userId);
    public int addProductInCarById1(@Param("userId") int userId,@Param("productId") int productId, @Param("quantity") int quantity);



    public List<Product>  getProductAndQuantityById(@Param("ids")int[] id);


    public List<Collections>  getConnectionOnlyById (@Param("userId") int userId, @Param("productId") int productId);



}
