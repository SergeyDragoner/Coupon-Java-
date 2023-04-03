package com.example.Coupon_Project.services;

import com.example.Coupon_Project.beans.Category;
import com.example.Coupon_Project.beans.Coupon;
import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.repositories.CompanyRepository;
import com.example.Coupon_Project.repositories.CouponRepository;
import com.example.Coupon_Project.repositories.CustomerRepository;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices extends ClientService {
    public CustomerServices(CompanyRepository companyServices, CustomerRepository customerServices, CouponRepository couponServices) {
        super(companyServices, customerServices, couponServices);
    }

    @Override
    public boolean login(String email, String password) {
        return customerRepository.existsByEmailAddressAndPassword(email, password);
    }


    public void purchaseCoupon(Coupon coupon) {

//            if(customerServices.existsByCouponsId(coupon.getId()))
//                System.out.println("You already bought that coupon");
//            else
//                System.out.println("You can buy that coupon");
    }

    public List<Coupon> getCustomerCoupons() {
        return null;
    }

    public List<Coupon> getCustomerCouponsByCategory(Category category) {
        return null;
    }

    public List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice){
        return null;
    }

    public Customer getCustomerDetails(){
        return null;
    }
}
