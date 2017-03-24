package com.hellospringdemo.dao;

import com.hellospringdemo.model.Product;

import java.util.List;

/**
 * Created by Hessam on 3/14/17.
 */
public interface ProductDao {
    void addProduct(Product product);
    Product getProductById(String id);
    List<Product> getAllProducts();
    void deleteProduct(String id);
    void editProduct(Product product);

}
