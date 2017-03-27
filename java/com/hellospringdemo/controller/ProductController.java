package com.hellospringdemo.controller;

import com.hellospringdemo.model.Product;
import com.hellospringdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Hessam on 3/27/17.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/productList")
    public String getProducts(Model model){
        List<Product> productList = productService.getProductList();
        model.addAttribute("products",productList);
        return "productList";
    }

}
