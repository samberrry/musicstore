package com.hellospringdemo.dao.Impl;

import com.hellospringdemo.dao.CartDao;
import com.hellospringdemo.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hessam on 3/18/17.
 */
@Repository
public class CartDaoImpl implements CartDao {
    private Map<String,Cart> listOfCarts;

    public CartDaoImpl() {
        listOfCarts = new HashMap<String, Cart>();
    }
    public Cart create (Cart cart)
    {
        if(listOfCarts.keySet().contains(cart.getCartId())){
            throw new IllegalArgumentException(String.format("Cant not create a cart. A cart with a given id($) already exists ",cart.getCartId()));
        }
        listOfCarts.put(cart.getCartId(),cart);
        return cart;
    }

    public Cart read(String cartId){
        return listOfCarts.get(cartId);
    }

    public void update(String cartId,Cart cart){
        if (!listOfCarts.keySet().contains(cartId)){
            throw new IllegalArgumentException(String.format("Cant not update  cart. A cart with a given id($) already does not exist ",cart.getCartId()));
        }
        listOfCarts.put(cartId,cart);//this also updates cart value with corresponding Id
    }

    public void delete(String cartId){
        if (!listOfCarts.keySet().contains(cartId)){
            throw new IllegalArgumentException(String.format("Cant delete cart A cart with a given id($) does not exist ",cartId));
        }
        listOfCarts.remove(cartId);
    }
}
