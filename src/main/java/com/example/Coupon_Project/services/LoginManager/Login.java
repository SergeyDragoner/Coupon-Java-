package com.example.Coupon_Project.services.LoginManager;

import com.example.Coupon_Project.exceptions.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.Administrator;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.CompanyServices;
import com.example.Coupon_Project.services.CustomerServices;
import org.springframework.context.annotation.Scope;

@Scope(value = "singleton")
public class Login {


    public ClientService login(String email, String password, ClientType clientType) throws ClientInfoIncorrectException {
        switch (clientType) {
            case Administrator:
                Administrator admin = new Administrator();
                if(admin.login(email, password))
                    return admin;
                else
                    throw new ClientInfoIncorrectException();

            case Company:
                CompanyServices company = new CompanyServices();
                if(company.login(email, password))
                    return company;
                else
                    throw new ClientInfoIncorrectException();

            case Customer:
                CustomerServices customer = new CustomerServices();
                if(customer.login(email, password))
                    return customer;
                else
                    throw new ClientInfoIncorrectException();
        }
        return null;
    }

}
