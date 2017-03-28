package com.hellospringdemo.dao.Impl;

import com.hellospringdemo.dao.CartDao;
import com.hellospringdemo.model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Hessam on 3/29/17.
 */
@Repository
@Transactional
public class CartDaoImpl implements CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Cart getCartById(int cartId){
        Session session = sessionFactory.getCurrentSession();
        return (Cart)session.get(Cart.class,cartId);
    }

    public void update(Cart cart){
        int cartid = cart.getCartId();
        //to do alter
    }
}
