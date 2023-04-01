package com.example.Coupon_Project.services;

import com.example.Coupon_Project.repositories.CompanyRepository;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServices extends ClientService {
    private CompanyRepository companyRepo;

    public CompanyServices() {
        this.companyRepo = companyRepo;
    }


    @Override
    public boolean login(String email, String password) {
        return false;
    }
}
