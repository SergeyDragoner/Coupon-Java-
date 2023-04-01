package com.example.Coupon_Project.services.ClientManager;

import com.example.Coupon_Project.repositories.CompanyRepository;
import com.example.Coupon_Project.repositories.CouponRepository;
import com.example.Coupon_Project.repositories.CustomerRepository;

public abstract class ClientService {

    protected CompanyRepository companyServices;
    protected CustomerRepository customerServices;
    protected CouponRepository couponServices;


    public abstract boolean login(String email, String password);

}
