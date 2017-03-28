package com.hellospringdemo.service;

import com.hellospringdemo.model.Cart;

/**
 * Created by Hessam on 3/29/17.
 */
public interface CartService {

    Cart getCartById(int cartId);

    void update(Cart cart);

}
