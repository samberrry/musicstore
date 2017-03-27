package com.hellospringdemo.controller.admin;

import com.hellospringdemo.model.Product;
import com.hellospringdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Hessam on 3/27/17.
 */
@Controller
@RequestMapping("/admin")
public class AdminProduct {

    @Autowired
    ProductService productService;

    private Path path;

    @RequestMapping("/product/addProduct")
    public String addProduct(Model model){
        Product product = new Product();
        product.setProductCategory("Instrument");
        product.setProductCondition("new");
        product.setProductStatus ("active");

        model.addAttribute("product",product);
        return "addProduct";
    }

    @RequestMapping(value = "/product/addProduct",method = RequestMethod.POST)
    public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, HttpServletRequest request){

        if (bindingResult.hasErrors())
        {
            return "addProduct";
        }

        productService.addProduct(product);

        MultipartFile productImage = product.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory+ "/WEB-INF/resources/images/00"+product.getProductId()+".png");

        if (productImage != null && !productImage.isEmpty())
        {
            try{
                productImage.transferTo(new File(path.toString()));
            }catch (Exception e)
            {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed");
            }
        }
        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/product/editProduct/{id}")
    public String editProduct(@PathVariable("id")int id, Model model){
        Product product = productService.getProductById(id);

        model.addAttribute("product",product);
        return "editProduct";
    }

    @RequestMapping(value = "/product/editProduct",method = RequestMethod.POST)
    public String editProductPost(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, HttpServletRequest request){

        if (bindingResult.hasErrors())
        {
            return "addProduct";
        }

        MultipartFile productImage = product.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory+ "/WEB-INF/resources/images/00"+product.getProductId()+".png");

        if (productImage != null && !productImage.isEmpty())
        {
            try{
                productImage.transferTo(new File(path.toString()));
            }catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed");
            }
        }

        productService.editProduct(product);

        return "redirect:/admin/productInventory";
    }

}
