package com.hellospringdemo.controller;

import com.hellospringdemo.dao.CartDao;
import com.hellospringdemo.dao.ProductDao;
import com.hellospringdemo.model.Cart;
import com.hellospringdemo.model.CartItem;
import com.hellospringdemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hessam on 3/20/17.
 */
@Controller
@RequestMapping("/rest/cart/")
public class CartController {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    //returning an object in form of json
    @RequestMapping(value = "/{cartId}",method = RequestMethod.GET)
    public @ResponseBody
    Cart read(@PathVariable(value = "cartId") String cartId)
    {
        return cartDao.read(cartId);
    }

    @RequestMapping(value = "/{cartId}",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "cartId") String cartId,@RequestBody Cart cart)
    {
        cartDao.update(cartId,cart);
    }

    @RequestMapping(value = "/{cartId}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "cartId")String cartId){
        cartDao.delete(cartId);
    }

    @RequestMapping(value = "/add/{productId}",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable(value = "productId")String productId, HttpServletRequest httpServletRequest){
        String sessionId = httpServletRequest.getSession().getId();
        Cart cart = cartDao.read(sessionId);

        if (cart == null)
        {
            cart = cartDao.create(new Cart(sessionId));
        }

        Product product = productDao.getProductById(productId);
        if (product == null)
        {
           throw new IllegalArgumentException(new Exception());
        }

        cart.addCartItem(new CartItem(product));

        cartDao.update(sessionId,cart);
    }

    @RequestMapping(value = "/remove/{productId}",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable(value = "productId")String productId,HttpServletRequest httpServletRequest)
    {
        String sessionId = httpServletRequest.getSession().getId();
        Cart cart = cartDao.read(sessionId);

        if (cart == null)
        {
//            cart = cartDao.create(new Cart(sessionId));
            throw new IllegalArgumentException(new Exception());
        }

        Product product = productDao.getProductById(productId);
        if (product == null)
        {
            throw new IllegalArgumentException(new Exception());
        }

        cart.removeCartItem(new CartItem(product));

        cartDao.update(sessionId,cart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Illegal resquest, please modify your payload")
    public void handleClientErrors(Exception e){}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,reason = "Illegal server error")
    public void handleServerError(Exception e){}


}
