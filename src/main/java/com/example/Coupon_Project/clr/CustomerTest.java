package com.example.Coupon_Project.clr;


import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.CustomerServices;
import com.example.Coupon_Project.services.LoginManager.Login;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class CustomerTest implements CommandLineRunner {

    private final Login login;
    private CustomerServices customerServices;

    public CustomerTest(Login login) {
        this.login = login;
    }

    @Override
    public void run(String... args) throws Exception {
//        loginTest();


    }

    public void loginTest() {
        try {
            this.customerServices = (CustomerServices) login.login("admin@admin.com", "admin", ClientType.Administrator);
            System.out.println("Customer Logged successfully");
        } catch (ClientInfoIncorrectException e) {
            System.out.println(e);
        }
    }



}
