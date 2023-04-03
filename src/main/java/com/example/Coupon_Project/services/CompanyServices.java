package com.example.Coupon_Project.services;

import com.example.Coupon_Project.beans.Coupon;
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
        return companyRepository.existsByEmailAddressAndPassword(email, password);
    }


    // TODO check if you need to pass companyId in each companyService methods
    public void addCoupon(Coupon coupon) {
        if (true)
            couponRepository.save(coupon);
    }
}
