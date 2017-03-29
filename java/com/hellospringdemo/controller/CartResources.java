package com.hellospringdemo.controller;

import com.hellospringdemo.model.Cart;
import com.hellospringdemo.model.CartItem;
import com.hellospringdemo.model.Customer;
import com.hellospringdemo.model.Product;
import com.hellospringdemo.service.CartItemService;
import com.hellospringdemo.service.CartService;
import com.hellospringdemo.service.CustomerService;
import com.hellospringdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hessam on 3/29/17.
 */
@Controller
@RequestMapping("/rest/cart")
public class CartResources {

    @Autowired
    CartService cartService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    CartItemService cartItemService;

    @RequestMapping("/{cartId}")
    //ResponseBody => convert to JSON with Jackson dependency
    public @ResponseBody
    Cart getCartById(@PathVariable(value = "cartId")int cartId){
        return cartService.getCartById(cartId);
    }

    @RequestMapping(value = "/add/{productId}",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable(value = "productId")int productId, @AuthenticationPrincipal User userActive){
        Customer customer = customerService.getCustomerByUsername(userActive.getUsername());
        Cart cart = customer.getCart();
        Product product = productService.getProductById(productId);

        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem cartItem:cartItems)
        {
            if (product.getProductId() == cartItem.getProduct().getProductId())
            {
                cartItem.setQuantity(cartItem.getQuantity()+1);
                cartItem.setTotalPrice(product.getProductPrice()*cartItem.getQuantity());
                cartItemService.addCartItem(cartItem);
                return;
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setTotalPrice(product.getProductPrice());
        cartItem.setCart(cart);
        cartItemService.addCartItem(cartItem);
    }

    @RequestMapping(value = "/remove/{productId}",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable int productId) {

        CartItem cartItem = cartItemService.getCartItemByProductId(productId);
        cartItemService.removeCartItem(cartItem);
    }

    @RequestMapping(value = "/{cartId}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void clearCart(@PathVariable int cartId){
        Cart cart = cartService.getCartById(cartId);
        cartItemService.removeAllCartItems(cart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Illegal request, please verify your payload")
    public void handleClientErrors(Exception e){}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,reason = "Internal Server Error")
    public void handleServerErrors(Exception e){}

}
