package com.example.Coupon_Project.services;

import com.example.Coupon_Project.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {
    private CustomerRepository customerRepo;

    public CustomerServices(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }
}
