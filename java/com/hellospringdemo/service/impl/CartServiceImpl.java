package com.hellospringdemo.service.impl;

import com.hellospringdemo.dao.CartDao;
import com.hellospringdemo.model.Cart;
import com.hellospringdemo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Hessam on 3/29/17.
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    public Cart getCartById(int cartId){
        return cartDao.getCartById(cartId);
    }

    public void update(Cart cart)
    {
        cartDao.update(cart);
    }



}
