package com.example.Coupon_Project;

import com.example.Coupon_Project.beans.Company;
import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.exceptions.companies.CompanyAlreadyExistException;
import com.example.Coupon_Project.exceptions.companies.CompanyDoesntExistException;
import com.example.Coupon_Project.exceptions.customers.CustomerAlreadyExistException;
import com.example.Coupon_Project.services.Administrator;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.LoginManager.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
public class CouponProjectApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx =  SpringApplication.run(CouponProjectApplication.class, args);
		Login log = ctx.getBean(Login.class);
		try {
			Administrator admin = (Administrator) log.login("admin@admin.com", "admin", ClientType.Administrator);

			Company cust = admin.getOneCompany(1);
			admin.deleteCustomer(1);
			System.out.println(cust);
			ArrayList<Customer> customers = (ArrayList<Customer>) admin.getAllCustomers();
			System.out.println(customers);
			admin.addCustomer(new Customer("Sergey", "Dragoner", "lalalala@gmail.com", "1234"));
			admin.addCompany(new Company("Nike", "JustDoIt@Adidas.das.com", "1234"));
		} catch (ClientInfoIncorrectException | CompanyAlreadyExistException | CustomerAlreadyExistException |
				 CompanyDoesntExistException e) {
			System.out.println(e.getMessage());
		}

//		Administrator client = ctx.getBean(Administrator.class);
//		try {
//			client.addCompany(new Company("Adidas", "JustDo", "ad4u"));
//		} catch (CompanyAlreadyExistException e) {
//			System.out.println(e.getMessage());
//		}


	}

}
