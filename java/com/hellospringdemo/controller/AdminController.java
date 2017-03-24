package com.hellospringdemo.controller;

import com.hellospringdemo.dao.ProductDao;
import com.hellospringdemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Hessam on 3/18/17.
 */
@Controller
public class AdminController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @RequestMapping("/admin/productInventory")
    public String productInventoryPage(Model model){
        List<Product> products = productDao.getAllProducts();
        model.addAttribute("products",products);
        return "productInventory";
    }

    @RequestMapping("admin/productInventory/addProduct")
    public String addProduct(Model model){
        Product product = new Product();
        product.setProductCategory("instrument");
        product.setProductCondition("new");
        product.setProductStatus("active");

        model.addAttribute("product",product);

        return "addProduct";
    }

    //@Valid checks that whether Model product is valid

    @RequestMapping(value = "admin/productInventory/addProduct",method = RequestMethod.POST)
    public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult){
        //, HttpServletRequest request
        if (bindingResult.hasErrors()){
            return "addProduct";
        }
        productDao.addProduct(product);

//         MultipartFile productImage = product.getProductImage();
//         String rootDirectory = request.getSession().getServletContext().getRealPath("/");
//        path = Paths.get(rootDirectory+"\\WEB-INF\\resources\\images\\"+product.getProductId()+".png");
//
//        if(productImage != null && !productImage.isEmpty())
//        {
//            try {
//                productImage.transferTo(new File(path.toString()));
//            }catch (Exception e)
//            {}
//        }

        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/admin/productInventory/deleteProduct/{id}")
    public String deleteProduct(@PathVariable String id){
        productDao.deleteProduct(id);
        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/admin/productInventory/editProduct/{id}")
    public String editProduct(@PathVariable("id") String id,Model model){
        Product product = productDao.getProductById(id);
        model.addAttribute(product);

        return"editProduct";
    }

    @RequestMapping(value = "/admin/productInventory/editProduct",method = RequestMethod.POST)
    public String editProductPost(@Valid @ModelAttribute("product")Product product,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "editProduct";
        }
        productDao.editProduct(product);
        return "redirect:/admin/productInventory";
    }
}
