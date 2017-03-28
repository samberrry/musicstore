package com.hellospringdemo.dao;

import com.hellospringdemo.model.Customer;

import java.util.List;

/**
 * Created by Hessam on 3/28/17.
 */
public interface CustomerDao {

    void addCustomer(Customer customer);

    Customer getCustomerById(int id);

    List<Customer> getAllCustomers();

    Customer getCustomerByUsername(String username);
}
