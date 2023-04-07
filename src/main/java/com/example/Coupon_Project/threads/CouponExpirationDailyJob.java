package com.example.Coupon_Project.threads;

import com.example.Coupon_Project.repositories.CouponRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CouponExpirationDailyJob {

    private final CouponRepository couponRepository;

    public CouponExpirationDailyJob(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24, initialDelay = 1000 * 60 * 5)
    public void deleteExpirationCoupons() {
        // TODO add query to delete expiration coupons

    }
}
