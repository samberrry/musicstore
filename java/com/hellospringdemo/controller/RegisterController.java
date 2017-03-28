package com.hellospringdemo.controller;

import com.hellospringdemo.model.BillingAddress;
import com.hellospringdemo.model.Customer;
import com.hellospringdemo.model.ShippingAddress;
import com.hellospringdemo.service.CustomerService;
import com.hellospringdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Hessam on 3/28/17.
 */
@Controller
public class RegisterController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/register")
    public String registerCustomer(Model model){

        Customer customer = new Customer();
        BillingAddress billingAddress = new BillingAddress();
        ShippingAddress shippingAddress = new ShippingAddress();
        customer.setBillingAddress(billingAddress);
        customer.setShippingAddress(shippingAddress);

        model.addAttribute("customer",customer);

        return "registerCustomer";
    }


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerCustomer(@ModelAttribute("customer")Customer customer, Model model){

        customer.setEnabled(true);

        customerService.addCustomer(customer);

        return "registerCustomerSuccess";
    }


}
