package com.example.Coupon_Project.services.LoginManager;

import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.AdminService;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.CompanyServices;
import com.example.Coupon_Project.services.CustomerServices;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Login {
    private final ApplicationContext ctx;

    public Login(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public ClientService login(String email, String password, ClientType clientType) throws ClientInfoIncorrectException {
        switch (clientType) {
            case Administrator:
                AdminService adminService = ctx.getBean(AdminService.class);
                if (adminService.login(email, password)) {
                    return adminService;
                }
                throw new ClientInfoIncorrectException();


            case Company:
                CompanyServices companyServices = ctx.getBean(CompanyServices.class);
                if (companyServices.login(email, password)) {
                    return companyServices;
                }
                throw new ClientInfoIncorrectException();

            case Customer:
                CustomerServices customerServices = ctx.getBean(CustomerServices.class);
                if (customerServices.login(email, password)) {
                    return customerServices;
                }
                throw new ClientInfoIncorrectException();

        }
        return null;
    }
}