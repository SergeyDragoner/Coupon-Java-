package com.example.Coupon_Project.services.LoginManager;

import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.AdminService;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.CompanyServices;
import com.example.Coupon_Project.services.CustomerServices;
import org.springframework.stereotype.Service;

@Service
public class Login {

    private final AdminService adminService;
    private final CompanyServices companyServices;
    private final CustomerServices customerServices;

    public Login(AdminService adminService, CompanyServices companyServices, CustomerServices customerServices) {
        this.adminService = adminService;
        this.companyServices = companyServices;
        this.customerServices = customerServices;
    }

    public ClientService login(String email, String password, ClientType clientType) throws ClientInfoIncorrectException {
        switch (clientType) {
            case Administrator:
                if (adminService.login(email, password)) {
                    System.out.println("Welcome Admin!");
                    return adminService;
                }
                throw new ClientInfoIncorrectException();


            case Company:
                if (companyServices.login(email, password)) {
                    System.out.println("Welcome Company!");
                    return companyServices;
                }
                throw new ClientInfoIncorrectException();

            case Customer:
                if (customerServices.login(email, password)) {
                    System.out.println("Welcome Customer!");
                    return customerServices;
                }
                throw new ClientInfoIncorrectException();

        }
        return null;
    }
}