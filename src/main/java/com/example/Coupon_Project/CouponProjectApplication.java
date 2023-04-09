package com.example.Coupon_Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponProjectApplication {

	public static void main(String[] args) {
		//The test are in the `clr` file!

		ConfigurableApplicationContext ctx =  SpringApplication.run(CouponProjectApplication.class, args);
		// Example of ctx configuration:
//		Login logger = ctx.getBean(Login.class);
//		try {
//			AdminService admin = (AdminService) logger.login("admin@admin.com", "admin", ClientType.Administrator);
//			CompanyServices company = (CompanyServices) logger.login("JustDoIt@Nike.com", "1231233", ClientType.Company);
//			CustomerServices customer = (CustomerServices) logger.login("bobProxy@Gmail.com", "1234", ClientType.Customer);
//		} catch (ClientInfoIncorrectException e) {
//			System.out.println(e.getMessage());
//		}
	}

}
