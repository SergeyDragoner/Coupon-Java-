package com.example.Coupon_Project;

import com.example.Coupon_Project.beans.Category;
import com.example.Coupon_Project.beans.Coupon;
import com.example.Coupon_Project.exceptions.coupons.CouponException;
import com.example.Coupon_Project.exceptions.customers.CustomerDoesntExistException;
import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.ClientManager.ClientType;
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

		////////////////////////////////////////////////////////////////////////////////////////////////
		//Company -> --> --->
//		try {
//			CompanyServices company = (CompanyServices) log.login("JustDoIt@Adidas.das.com", "1234", ClientType.Company);
//			ArrayList<Coupon> companyCoupons = (ArrayList<Coupon>) company.getCompanyCoupons();
//			System.out.println("All company coupons: ");
//			System.out.println(companyCoupons);
//			ArrayList<Coupon> couponsByCategory = (ArrayList<Coupon>) company.getCompanyCouponsByCategory(Category.Electricity);
//			System.out.println("Category: ");
//			System.out.println(couponsByCategory);
//			ArrayList<Coupon> companyCouponsByMaxPrice = (ArrayList<Coupon>) company.getCompanyCouponsByMaxPrice(32.99);
//			System.out.println("Max Price 32.99$: " );
//			System.out.println(companyCouponsByMaxPrice);
//			Company comp = company.getCompanyDetails();
//			System.out.println("Company details: ");
//			System.out.println(comp);
//			Company companyNike = new Company("Nike", "JustDoIt", "ad4u");
//
//			Coupon coupon_1 = new Coupon(companyNike.getId(), Category.Electricity, "Charger", "Fastest Charger on planet", Date.valueOf("2023-04-03"),Date.valueOf("2023-12-12") , 12, 35.99, "");
//
//		} catch (ClientInfoIncorrectException | CompanyDoesntExistException e) {
//			System.out.println(e.getMessage());
//		}

		////////////////////////////////////////////////////////////////////////////////////////////////
		//Customer -> --> -->
		try {
			CustomerServices customer = (CustomerServices) log.login("bobM@Gmail.com", "b4abc", ClientType.Customer);
			Coupon scoup = new Coupon(1, Category.Electricity, "Charger", "Fastest Charger on planet", Date.valueOf("2023-04-03"),Date.valueOf("2023-12-12") , 4, 35.99, "");
			customer.purchaseCoupon(scoup);
//			ArrayList<Coupon> customerCoupons = (ArrayList<Coupon>) customer.getCustomerCoupons();
//			System.out.println(customerCoupons);

		} catch (ClientInfoIncorrectException | CustomerDoesntExistException | CouponException e) {
			System.out.println(e.getMessage());
		}

	}

}
