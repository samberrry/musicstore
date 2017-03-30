package com.hellospringdemo.controller;

import com.hellospringdemo.model.Customer;
import com.hellospringdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Hessam on 3/28/17.
 */
@Controller
@RequestMapping("/customer/cart")
public class CartController {

    @Autowired
    private CustomerService customerService;

//    @RequestMapping
//    public String getCart(@AuthenticationPrincipal User activeUser){
//
//        Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
//        int cartId = customer.getCart().getCartId();
//
//        return "redirect:/customer/cart/"+cartId;
//    }

    @RequestMapping
    public String getCart(Principal principal){
        Customer customer = customerService.getCustomerByUsername(principal.getName());
        int cartId = customer.getCart().getCartId();

        return "redirect:/customer/cart/"+cartId;
    }


    @RequestMapping("/{cartId}")
    public String getCart(@PathVariable(value = "cartId")int cartId, Model model){

        model.addAttribute("cartId",cartId);
        return "cart";
    }


}
