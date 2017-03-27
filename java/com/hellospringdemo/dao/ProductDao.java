package com.hellospringdemo.dao;

import com.hellospringdemo.model.Product;

import java.util.List;

/**
 * Created by Hessam on 3/14/17.
 */
public interface ProductDao {
    List<Product> getProductList();

    Product getProductById(int id);

    void addProduct(Product product);

    void editProduct(Product product);

    void deleteProduct(Product product);

}
