package com.example.Coupon_Project.services;

import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {
    private CustomerRepository customerRepo;

    public CustomerServices(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }


    public void addCustomer(Customer customer) {
        customerRepo.save(customer);
    }
}
