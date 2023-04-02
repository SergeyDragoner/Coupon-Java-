package com.example.Coupon_Project.services.LoginManager;

import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.Administrator;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.CompanyServices;
import com.example.Coupon_Project.services.CustomerServices;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.example.Coupon_Project.services")
public class Login {

    private final Administrator administrator;
    private final CompanyServices companyServices;
    private final CustomerServices customerServices;

    public Login(Administrator administrator, CompanyServices companyServices, CustomerServices customerServices) {
        this.administrator = administrator;
        this.companyServices = companyServices;
        this.customerServices = customerServices;
    }

    public ClientService login(String email, String password, ClientType clientType) throws ClientInfoIncorrectException {
        switch (clientType) {
            case Administrator:
                if(administrator.login(email, password)) {
                    System.out.println("Welcome Admin!");
                    return administrator;
                } else {
                    throw new ClientInfoIncorrectException();
                }

            case Company:
                if(companyServices.login(email, password)) {
                    return companyServices;
                } else {
                    throw new ClientInfoIncorrectException();
                }

            case Customer:
                if(customerServices.login(email, password)) {
                    return customerServices;
                } else {
                    throw new ClientInfoIncorrectException();
                }
        }
        return null;
    }
}