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

        ConfigurableApplicationContext ctx = SpringApplication.run(CouponProjectApplication.class, args);
        // Unique numbers
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        int[] array = {50, 1, 2, 3, 4, 3, 4, 5, 7, 9, 9, 7, 9, 8, 10, 8, 10, 11, 12, 32, 1};
//        Arrays.sort(array);
//        for (int i = array.length - 1; i > 0; i--) {
//            if(array[i] == array[i -1])
//                array[i] = 0;
//            System.out.print(array[i] == 0 ? "" : array[i] + " ");
//        }

//        for (int i = array.length - 1; i > 0; i--) {
//            for (int j = i - 1; j >= 0; j--) {
//                if (array[i] != null && array[i].equals(array[j])) {
//                    array[j] = null;
//                }
//            }
//        }
//        Arrays.stream(array)
//                .filter(Objects::nonNull)
//                .forEach(val -> System.out.print(val + " "));

//        int i = array.length - 1;
//        while (i > 0) {
//            if (array[i] != null) {
//                for (int j = i - 1; j >= 0; j--) {
//                    if (array[j] != null && array[i].equalsIgnoreCase(array[j])) {
//                        array[j] = null;
//                    }
//                }
//            }
//            i--;
//        }
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        Arrays.stream(array)
//                .distinct()
//                .forEach(val -> System.out.print(val + " "));

        ///////////////////////////////////////////////////////
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
