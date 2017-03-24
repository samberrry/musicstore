package com.hellospringdemo.dao;

import com.hellospringdemo.model.Cart;

/**
 * Created by Hessam on 3/18/17.
 */
public interface CartDao {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId,Cart cart);
    void delete(String cartId);
}
