package com.example.Coupon_Project.services;

import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.repositories.CustomerRepository;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices extends ClientService {
    private CustomerRepository customerRepo;

    public CustomerServices() {
        this.customerRepo = customerRepo;
    }


    public void addCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
}
