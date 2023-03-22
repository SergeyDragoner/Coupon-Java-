package com.example.Coupon_Project.services;

import com.example.Coupon_Project.repositories.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponServices {
    private CouponRepository couponRepo;

    public CouponServices(CouponRepository couponRepo) {
        this.couponRepo = couponRepo;
    }
}
