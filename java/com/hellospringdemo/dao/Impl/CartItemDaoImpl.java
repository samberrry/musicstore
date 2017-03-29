package com.hellospringdemo.dao.Impl;

import com.hellospringdemo.dao.CartItemDao;
import com.hellospringdemo.model.Cart;
import com.hellospringdemo.model.CartItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Hessam on 3/29/17.
 */
@Repository
@Transactional
public class CartItemDaoImpl implements CartItemDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addCartItem(CartItem cartItem){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cartItem);
        session.flush();
    }

    public void removeCartItem(CartItem cartItem){
        Session session = sessionFactory.getCurrentSession();
        session.delete(cartItem);
        session.flush();
    }

    public void removeAllCartItems(Cart cart){
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems)
        {
            removeCartItem(cartItem);
        }
    }

}
