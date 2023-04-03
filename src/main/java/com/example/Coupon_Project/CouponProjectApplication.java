package com.example.Coupon_Project;

import com.example.Coupon_Project.beans.Category;
import com.example.Coupon_Project.beans.Company;
import com.example.Coupon_Project.beans.Coupon;
import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.CompanyServices;
import com.example.Coupon_Project.services.CustomerServices;
import com.example.Coupon_Project.services.LoginManager.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Date;

@SpringBootApplication
@EnableScheduling
public class CouponProjectApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx =  SpringApplication.run(CouponProjectApplication.class, args);
		Login log = ctx.getBean(Login.class);

		//Administrator -> --> --->
//		try {
//			Administrator admin = (Administrator) log.login("admin@admin.com", "admin", ClientType.Administrator);
//
//			Company customer_1 = admin.getOneCompany(1);
//			admin.deleteCustomer(1);
//			System.out.println(customer_1);
//			ArrayList<Customer> customers = (ArrayList<Customer>) admin.getAllCustomers();
//			System.out.println(customers);
//			admin.addCustomer(new Customer("Sergey", "Dragoner", "lalalala@gmail.com", "1234"));
//			admin.addCompany(new Company("Nike", "JustDoIt@Adidas.das.com", "1234"));
//		} catch (ClientInfoIncorrectException | CompanyAlreadyExistException | CustomerAlreadyExistException |
//				 CompanyDoesntExistException e) {
//			System.out.println(e.getMessage());
//		}


		//Company -> --> --->
		try {
			CompanyServices company = (CompanyServices) log.login("JustDo", "ad4u", ClientType.Company);
			Company companyNike = new Company("Nike", "JustDoIt", "ad4u");
			companyNike.setId(1);
			Coupon coupon_1 = new Coupon(companyNike.getId(), Category.Electricity, "Charger", "Fastest Charger on planet", Date.valueOf("2023-04-03"),Date.valueOf("2023-12-12") , 12, 35.99, "");
			company.addCoupon(coupon_1);

		} catch (ClientInfoIncorrectException e) {
			System.out.println(e.getMessage());
		}

		//Customer -> --> -->
		try {
			CustomerServices customer = (CustomerServices) log.login("lalalala@gmail.com", "1234", ClientType.Customer);


		} catch (ClientInfoIncorrectException e) {
			System.out.println(e.getMessage());
		}

	}

}
