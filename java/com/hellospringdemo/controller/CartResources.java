package com.hellospringdemo.controller;

import com.hellospringdemo.model.Cart;
import com.hellospringdemo.model.CartItem;
import com.hellospringdemo.model.Customer;
import com.hellospringdemo.model.Product;
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
            }
        }

    }

}
