package com.example.Coupon_Project;

import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.CompanyServices;
import com.example.Coupon_Project.services.CustomerServices;
import com.example.Coupon_Project.services.LoginManager.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CouponProjectApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx =  SpringApplication.run(CouponProjectApplication.class, args);
		Login log = ctx.getBean(Login.class);

		//Administrator -> --> --->
//		try {
//			Administrator admin = (Administrator) log.login("admin@admin.com", "admin", ClientType.Administrator);
//
//			Company cust = admin.getOneCompany(1);
//			admin.deleteCustomer(1);
//			System.out.println(cust);
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
