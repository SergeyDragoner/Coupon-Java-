package com.example.Coupon_Project.services;

import com.example.Coupon_Project.repositories.CompanyRepository;
import com.example.Coupon_Project.repositories.CouponRepository;
import com.example.Coupon_Project.repositories.CustomerRepository;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServices extends ClientService {

    public CompanyServices(CompanyRepository companyServices, CustomerRepository customerServices, CouponRepository couponServices) {
        super(companyServices, customerServices, couponServices);
    }

    @Override
    public boolean login(String email, String password) {
        return companyServices.existsByEmailAddressAndPassword(email, password);
    }
}
