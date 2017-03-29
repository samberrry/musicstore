package com.hellospringdemo.service.impl;

import com.hellospringdemo.dao.CartItemDao;
import com.hellospringdemo.model.Cart;
import com.hellospringdemo.model.CartItem;
import com.hellospringdemo.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Hessam on 3/29/17.
 */
@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    private CartItemDao cartItemDao;

    public void addCartItem(CartItem cartItem){

    }

    public void removeCartItem(CartItem cartItem){

    }

    public void removeAllCartItems(Cart cart){

    }

}
