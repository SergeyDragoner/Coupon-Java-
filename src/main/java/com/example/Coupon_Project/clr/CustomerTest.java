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
        loginTest();
        purchaseCouponFromCompany(1);

    }

    public void loginTest() {
        try {
            this.customerServices = (CustomerServices) login.login("bobProxy@Gmail.com", "1234", ClientType.Customer);
            System.out.println("Customer Logged successfully");
        } catch (ClientInfoIncorrectException e) {
            System.out.println(e);
        }
    }


    public void purchaseCouponFromCompany(int companyId) {
        try {
//            this.customerServices.purchaseCouponForCustomer(this.customerServices.getCustomerDetails().getId(), new Coupon(companyId, Category.Electricity, "chargira", "Fastest Charger on planet", Date.valueOf("2023-04-03"), Date.valueOf("2023-12-12"), 4, 35.99, ""));
            this.customerServices.purchaseCouponForCustomer(this.customerServices.getCustomerDetails().getId(), new Coupon(companyId, Category.Electricity, "Bike", "Fastest Bike on planet", Date.valueOf("2023-04-03"), Date.valueOf("2023-04-08"), 400, 3599.99, ""));
//            this.customerServices.purchaseCouponForCustomer(this.customerServices.getCustomerDetails().getId(), new Coupon(companyId, Category.SPA, "Relax", "asd", Date.valueOf("2022-01-01"), Date.valueOf("2023-04-04"), 1212, 29.99, ""));
//            this.customerServices.purchaseCouponForCustomer(this.customerServices.getCustomerDetails().getId(), new Coupon(companyId, Category.Electricity, "Charger", "Fastest Charger on planet", Date.valueOf("2023-04-03"), Date.valueOf("2023-12-12"), 4, 35.99, ""));
        } catch (CouponException | CustomerDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

}
