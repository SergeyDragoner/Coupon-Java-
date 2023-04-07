package com.example.Coupon_Project.clr;


import com.example.Coupon_Project.beans.Category;
import com.example.Coupon_Project.beans.Coupon;
import com.example.Coupon_Project.exceptions.coupons.CouponException;
import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.CompanyServices;
import com.example.Coupon_Project.services.LoginManager.Login;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
@Order(2)
public class CompanyTest implements CommandLineRunner {


    private final Login login;
    private CompanyServices companyServices;

    public CompanyTest(Login login) {
        this.login = login;
    }

    @Override
    public void run(String... args) throws Exception {
//        loginTest();
//        addCoupon();
    }

    public void loginTest() {
        try {
            this.companyServices = (CompanyServices) login.login("JustDoIt@Adidas.das.com", "1234", ClientType.Company);
            System.out.println("Company Logged successfully");
        } catch (ClientInfoIncorrectException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void addCoupon(){
        try {
            this.companyServices.addCoupon(new Coupon(this.companyServices.getCompanyId(), Category.Electricity, "Charger", "Fastest Charger on planet", Date.valueOf("2023-04-03"),Date.valueOf("2023-12-12") , 4, 35.99, ""));
        } catch (CouponException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCoupon(Coupon coupon){
        try {
            this.companyServices.updateCoupon(coupon);
        } catch (CouponException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void deleteCoupon(int id){
        try {
            this.companyServices.deleteCoupon(id);
        } catch (CouponException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public List<Coupon> getCompanyCoupons(){
        return this.companyServices.getCompanyCoupons();
    }

    public List<Coupon> getCompanyCouponsByCategory(Category category){
        return this.companyServices.getCompanyCouponsByCategory(category);
    }
}
