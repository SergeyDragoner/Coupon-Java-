package com.example.Coupon_Project.clr;


import com.example.Coupon_Project.beans.Category;
import com.example.Coupon_Project.beans.Coupon;
import com.example.Coupon_Project.exceptions.coupons.CouponException;
import com.example.Coupon_Project.exceptions.customers.CustomerDoesntExistException;
import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.CustomerServices;
import com.example.Coupon_Project.services.LoginManager.Login;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Order(3)
public class CustomerTest implements CommandLineRunner {

    private final Login login;
    private CustomerServices customerServices;

    public CustomerTest(Login login) {
        this.login = login;
    }

    @Override
    public void run(String... args) {
        System.out.println("---------------- Customer Services ----------------");
        if (loginTest()) {
            purchaseCouponFromCompanyTest(3);
            getCustomerCouponsTest();
            getCustomerCouponsByCategoryTest(Category.Electricity);
            getCustomerCouponsByMaxPriceTest(12.99);
            getCustomerDetailsTest();
        }
        System.out.println("----------------End Of Customer Test ----------------\n\n");

    }

    public boolean loginTest() {
        try {
            this.customerServices = (CustomerServices) login.login("mishigine@gmail.com", "1234", ClientType.Customer);
            System.out.println("Customer " + this.customerServices.getCustomerDetails().getEmailAddress() + " Logged in successfully");
            return true;
        } catch (ClientInfoIncorrectException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public void purchaseCouponFromCompanyTest(int companyId) {
        try {
            this.customerServices.purchaseCouponForCustomer(this.customerServices.getCustomerId(), new Coupon(companyId, Category.Electricity, "chargira", "Fastest Charger on planet", Date.valueOf("2023-04-03"), Date.valueOf("2023-12-12"), 4, 35.99, ""));
            this.customerServices.purchaseCouponForCustomer(this.customerServices.getCustomerId(), new Coupon(companyId, Category.Electricity, "Biky", "First Ever fastest Bike EVER!!", Date.valueOf("2023-04-03"), Date.valueOf("2023-04-08"), 400, 3599.99, ""));
//            this.customerServices.purchaseCouponForCustomer(this.customerServices.getCustomerId(), new Coupon(companyId, Category.SPA, "Relax", "asd", Date.valueOf("2022-01-01"), Date.valueOf("2023-04-04"), 1212, 29.99, ""));
//            this.customerServices.purchaseCouponForCustomer(this.customerServices.getCustomerId(), new Coupon(companyId, Category.Electricity, "Charger", "Fastest Charger on planet", Date.valueOf("2023-04-03"), Date.valueOf("2023-12-12"), 4, 35.99, ""));
        } catch (CouponException | CustomerDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getCustomerCouponsTest() {
        try {
            System.out.println(this.customerServices.getCustomerCoupons());
        } catch (CustomerDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getCustomerCouponsByCategoryTest(Category category) {
        try {
            System.out.println("Customer Coupons by category " + category);
            System.out.println(this.customerServices.getCustomerCouponsByCategory(category));
        } catch (CustomerDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getCustomerCouponsByMaxPriceTest(double maxPrice) {
        System.out.println("All the customers coupons to price: " + maxPrice);
        try {
            System.out.println(this.customerServices.getCustomerCouponsByMaxPrice(maxPrice));
        } catch (CustomerDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getCustomerDetailsTest() {
        System.out.println("Customer Information: \n" + this.customerServices.getCustomerDetails());
    }
}
