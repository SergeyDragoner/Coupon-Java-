package com.example.Coupon_Project.threads;

import com.example.Coupon_Project.beans.Coupon;
import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.repositories.CouponRepository;
import com.example.Coupon_Project.repositories.CustomerRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CouponExpirationDailyJob {

    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;

    public CouponExpirationDailyJob(CouponRepository couponRepository, CustomerRepository customerRepository) {
        this.couponRepository = couponRepository;
        this.customerRepository = customerRepository;
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24, initialDelay = 1000 * 20)
    public void removeExpiredCouponsFromCustomerAccountsAndDelete() {
        Date currentDate = new Date(System.currentTimeMillis());
        List<Coupon> expiredCoupons = couponRepository.findAll();
        List<Customer> allCustomers = customerRepository.findAll();
        for (Coupon expiredCoupon : expiredCoupons) {
            if (expiredCoupon.getEndDate().before(currentDate)) {
                for (Customer customer : allCustomers) {
                    List<Coupon> customerCoupons = customer.getCoupons();
                    customerCoupons.removeIf(coupon -> coupon.getId() == expiredCoupon.getId());
                    customerRepository.save(customer);
                }
                couponRepository.deleteById(expiredCoupon.getId());
                if (!couponRepository.existsById(expiredCoupon.getId()))
                    System.out.println("Coupon - " + expiredCoupon.getId() + " : " + expiredCoupon.getTitle() + " deleted successfully. Expired date: " + expiredCoupon.getEndDate());
            }
        }
    }
}