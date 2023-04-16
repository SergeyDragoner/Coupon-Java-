package com.example.Coupon_Project.clr;


import com.example.Coupon_Project.beans.Category;
import com.example.Coupon_Project.beans.Coupon;
import com.example.Coupon_Project.exceptions.companies.CompanyDoesntExistException;
import com.example.Coupon_Project.exceptions.coupons.CouponException;
import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.CompanyServices;
import com.example.Coupon_Project.services.LoginManager.Login;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Order(2)
public class CompanyTest implements CommandLineRunner {
    private final Login login;
    private CompanyServices companyServices;

    public CompanyTest(Login login) {
        this.login = login;
    }

    private Coupon couponBike;

    @Override
    public void run(String... args) {
        System.out.println("---------------- Company Services ----------------");

        if (loginTest()) {
            addCouponTest();
            //Making changes for the company Coupon in order to update:
            couponBike.setTitle("Biky");
            couponBike.setAmount(1400);
            couponBike.setDescription("First Ever fastest Bike EVER!!");
            couponBike.setEndDate(Date.valueOf("2024-01-01"));
            updateCouponTest(couponBike);
//            deleteCouponTest(2);
            getCompanyCouponsTest();
            getCompanyCouponsByCategoryTest(Category.Restaurant);
            getCompanyCouponsByMaxPriceTest(22.89);
            getCompanyDetailsTest();
        }
        System.out.println("----------------End Of Company Test ----------------\n\n");

    }


    public boolean loginTest() {
        try {
//            this.companyServices = (CompanyServices) login.login("JustDoIt@Adidas.das.com", "1234", ClientType.Company);
//            this.companyServices = (CompanyServices) login.login("JustDoIt@Nike.com", "1231233", ClientType.Company);
            this.companyServices = (CompanyServices) login.login("JeffyJeff@gmail.com", "4321", ClientType.Company);
            System.out.println("Company "+ this.companyServices.getCompanyDetails().getName()+" Logged successfully");
            return true;
        } catch (ClientInfoIncorrectException | CompanyDoesntExistException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    public void addCouponTest() {
        try {
            couponBike = new Coupon(this.companyServices.getCompanyId(), Category.Electricity, "Bike", "Fastest Bike on planet", Date.valueOf("2023-04-08"), Date.valueOf("2023-04-18"), 400, 3599.99, "");
            this.companyServices.addCoupon(new Coupon(this.companyServices.getCompanyId(), Category.Electricity, "chargira", "Fastest Charger on planet", Date.valueOf("2023-04-08"), Date.valueOf("2023-04-30"), 4, 35.99, ""));
            this.companyServices.addCoupon(couponBike);
        } catch (CouponException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCouponTest(Coupon coupon) {
        try {
            this.companyServices.updateCoupon(coupon);
        } catch (CouponException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void deleteCouponTest(int id) {
        try {
            System.out.println("Deleting Coupon: " + id);
            this.companyServices.deleteCoupon(id);
        } catch (CouponException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void getCompanyCouponsTest() {
        System.out.println("All Company Coupons: \n" +this.companyServices.getCompanyCoupons());
    }

    public void getCompanyCouponsByCategoryTest(Category category) {
//        System.out.println("Company Coupons by category: " + category);
        System.out.println("Company Coupons by category: " + category + "\n"+ this.companyServices.getCompanyCouponsByCategory(category));
    }

    public void getCompanyCouponsByMaxPriceTest(double maxPrice) {
        System.out.println("Company Coupons by maxPrice: " + maxPrice);
        System.out.println(this.companyServices.getCompanyCouponsByMaxPrice(maxPrice));
    }

    public void getCompanyDetailsTest() {
        try {
//            this.companyServices.getCompanyId();
            System.out.println("Company Details: \n{" + this.companyServices.getCompanyDetails() + " }");
        } catch (CompanyDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

}
