package com.hellospringdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hessam on 3/20/17.
 */
@Controller
@RequestMapping("/cart")
public class CartItemController {
    @RequestMapping
    public String get(HttpServletRequest httpServletRequest){
        //we use session ID as cart Id
        return "redirect:/cart/"+httpServletRequest.getSession(true).getId();
    }

    @RequestMapping(value = "/{cartId}",method = RequestMethod.GET)
    public String getCart(@PathVariable(value = "cartId") String cartId, Model model){
        model.addAttribute("cartId",cartId);
        return "cart";
    }

}
