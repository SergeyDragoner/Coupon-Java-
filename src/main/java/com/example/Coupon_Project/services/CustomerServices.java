package com.example.Coupon_Project.services;

import com.example.Coupon_Project.beans.Category;
import com.example.Coupon_Project.beans.Coupon;
import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.exceptions.coupons.CouponException;
import com.example.Coupon_Project.exceptions.customers.CustomerDoesntExistException;
import com.example.Coupon_Project.repositories.CompanyRepository;
import com.example.Coupon_Project.repositories.CouponRepository;
import com.example.Coupon_Project.repositories.CustomerRepository;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class CustomerServices extends ClientService {
    private int customerId = 0;

    public CustomerServices(CompanyRepository companyServices, CustomerRepository customerServices, CouponRepository couponServices) {
        super(companyServices, customerServices, couponServices);
    }

    @Override
    public boolean login(String email, String password) {
        if (customerRepository.existsByEmailAddressAndPassword(email, password))
            customerId = customerRepository.getCustomerByEmailAddressAndPassword(email, password).getId();
        return customerRepository.existsByEmailAddressAndPassword(email, password);
    }


    @Transactional
    public void purchaseCoupon(Coupon coupon) throws CustomerDoesntExistException, CouponException {
        Date currentDate = new Date(System.currentTimeMillis());
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerDoesntExistException();
        }
        Customer customer = customerRepository.findById(customerId).get();
        if (couponRepository.existsByTitleAndDescriptionAndCompany_Id(coupon.getTitle(), coupon.getDescription(), coupon.getCompanyId())) {
            Coupon couponToPurchase = couponRepository.getByTitleAndDescriptionAndCompany_Id(coupon.getTitle(), coupon.getDescription(), coupon.getCompanyId());
            if (!customer.getCoupons().contains(couponToPurchase)) {
                if (couponToPurchase.getAmount() > 0) {
                    if (couponToPurchase.getEndDate().after(currentDate)) {
                        couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
                        customer.getCoupons().add(couponToPurchase);
                        customerRepository.save(customer);
                    } else {
                        throw new CouponException("The coupon date is EXPIRED!");
                    }
                } else {
                    throw new CouponException("The coupon is out of STOCK!");
                }
            } else {
                throw new CouponException("Coupon " + couponToPurchase.getId() + " already purchased by customer " + customerId);
            }
        } else {
            throw new CouponException("The coupon doesn't exist");
        }
    }

    public List<Coupon> getCustomerCoupons() {
        return null;
    }

    public List<Coupon> getCustomerCouponsByCategory(Category category) {
        return null;
    }

    public List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) {
        return null;
    }

    public Customer getCustomerDetails() {
        return customerRepository.findById(customerId).orElseThrow();
    }
}
