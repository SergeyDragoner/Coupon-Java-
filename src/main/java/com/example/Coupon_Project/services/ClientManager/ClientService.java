package com.example.Coupon_Project.services.ClientManager;

import com.example.Coupon_Project.repositories.CompanyRepository;
import com.example.Coupon_Project.repositories.CouponRepository;
import com.example.Coupon_Project.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {

    protected CompanyRepository companyRepository;

    protected CustomerRepository customerRepository;

    protected CouponRepository couponRepository;

    public ClientService(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
    }

    public abstract boolean login(String email, String password);

}
