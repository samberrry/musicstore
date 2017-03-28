package com.hellospringdemo.service;

import com.hellospringdemo.model.Customer;

import java.util.List;

/**
 * Created by Hessam on 3/28/17.
 */
public interface CustomerService {
    void addCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();
}
