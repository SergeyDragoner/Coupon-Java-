package com.example.Coupon_Project.clr;


import com.example.Coupon_Project.beans.Company;
import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.exceptions.companies.CompanyAlreadyExistException;
import com.example.Coupon_Project.exceptions.companies.CompanyDoesntExistException;
import com.example.Coupon_Project.exceptions.customers.CustomerAlreadyExistException;
import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.AdminService;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.LoginManager.Login;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AdminTest implements CommandLineRunner {

    private final Login login;
    private AdminService adminService;

    public AdminTest(Login login) {
        this.login = login;
    }

    @Override
    public void run(String... args) throws Exception {
        loginTest();
//        addCompanyTest();
//        addCustomerTest();
        deleteCompany(1);
    }

    public void loginTest() {
        try {
            this.adminService = (AdminService) login.login("admin@admin.com", "admin", ClientType.Administrator);
            System.out.println("Admin Logged successfully");
        } catch (ClientInfoIncorrectException e) {
            System.out.println(e);
        }
    }

    public void addCompanyTest() {

        try {
            this.adminService.addCompany(new Company("Niky", "JustDoIt@Nike.com", "1231233"));
        } catch (CompanyAlreadyExistException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void addCustomerTest() {
        try {
            this.adminService.addCustomer(new Customer("Java", "Proxy", "bobProxy@Gmail.com", "1234"));
        } catch (CustomerAlreadyExistException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void deleteCompany(int id){
        try {
            this.adminService.deleteCompany(id);
        } catch (CompanyDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

}
