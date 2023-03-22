package com.example.Coupon_Project.services;

import com.example.Coupon_Project.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyServices {
    private CompanyRepository companyRepo;

    public CompanyServices(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }


}
