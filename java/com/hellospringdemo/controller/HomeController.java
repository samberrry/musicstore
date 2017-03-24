package com.hellospringdemo.controller;

import com.hellospringdemo.dao.ProductDao;
import com.hellospringdemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.util.List;

/**
 * Created by Hessam on 3/11/17.
 */

@Controller
public class HomeController {

//    private Path path;

    @Autowired
    private ProductDao productDao;

    @RequestMapping("/")
    public String home(){
        return "home";}

     @RequestMapping("/productList")
    public String getProducts(Model model){
         List<Product> products = productDao.getAllProducts();
         model.addAttribute("products",products);
        return "productList";
     }
     @RequestMapping("/productList/viewProduct/{productId}")
    public String viewProduct(@PathVariable String productId,Model model) throws IOException {
        Product product = productDao.getProductById(productId);
        model.addAttribute(product);

        return "viewProduct";
     }

}
