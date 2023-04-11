package com.example.Coupon_Project.clr;


import com.example.Coupon_Project.beans.Company;
import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.exceptions.companies.CompanyAlreadyExistException;
import com.example.Coupon_Project.exceptions.companies.CompanyDoesntExistException;
import com.example.Coupon_Project.exceptions.companies.CompanyNotAllowedToBeChangedException;
import com.example.Coupon_Project.exceptions.customers.CustomerAlreadyExistException;
import com.example.Coupon_Project.exceptions.customers.CustomerDoesntExistException;
import com.example.Coupon_Project.exceptions.login.ClientInfoIncorrectException;
import com.example.Coupon_Project.services.AdminService;
import com.example.Coupon_Project.services.ClientManager.ClientType;
import com.example.Coupon_Project.services.LoginManager.Login;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AdminTest implements CommandLineRunner {

    private final Login login;
    private AdminService adminService;

    public AdminTest(Login login) {
        this.login = login;
    }

    private Company company;
    private Customer customer;

    @Override
    public void run(String... args){
        System.out.println("---------------- Admin Services ----------------");

        if (loginTest()) {
            ///////////////////////////////Company Services ////////////////////////////////
            addCompanyTest();
            company.setPassword("4321");
            company.setEmailAddress("JeffyJeff@gmail.com");
            updateCompanyTest(company);
            deleteCompany(1);
            getAllCompaniesTest();
            getOneCompanyTest(2);
            ///////////////////////////////Customer Services ////////////////////////////////
            addCustomerTest();
            customer.setFirstName("Baby Shark");
            updateCustomerTest(customer);
            deleteCustomerTest(1);
            getAllCustomersTest();
            getOneCustomerTest(2);
        }
        System.out.println("----------------End Of Admin Test ----------------");
    }

    public boolean loginTest() {
        try {
            this.adminService = (AdminService) login.login("admin@admin.com", "admin", ClientType.Administrator);
            System.out.println("Admin Logged successfully");
            return true;
        } catch (ClientInfoIncorrectException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addCompanyTest() {

        try {
            company = new Company("MyNameIsJeff", "jeff@gmail.com", "1234");
            this.adminService.addCompany(new Company("Niky", "JustDoIt@Nike.com", "1231233"));
            this.adminService.addCompany(new Company("TheRock", "TheRock@rockyRock.gmail.com", "bigRock"));
            this.adminService.addCompany(company);
        } catch (CompanyAlreadyExistException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void updateCompanyTest(Company company) {
        try {
            this.adminService.updateCompany(company);
        } catch (CompanyDoesntExistException | CompanyNotAllowedToBeChangedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCompany(int id) {
        try {
            this.adminService.deleteCompany(id);
        } catch (CompanyDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllCompaniesTest() {
        this.adminService.getAllCompanies().forEach(System.out::println);
    }

    public void getOneCompanyTest(int companyID) {
        try {
            System.out.println(this.adminService.getOneCompany(companyID));
        } catch (CompanyDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addCustomerTest() {
        try {
            customer = new Customer("Java", "mish", "mishigine@gmail.com", "1234");
            this.adminService.addCustomer(new Customer("Java", "Proxy", "bobProxy@Gmail.com", "1234"));
            this.adminService.addCustomer(new Customer("TeSvA", "YX", "tesva@Gmail.com", "1234"));
            this.adminService.addCustomer(customer);
        } catch (CustomerAlreadyExistException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void updateCustomerTest(Customer customer) {
        try {
            this.adminService.updateCustomer(customer);
        } catch (CustomerDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomerTest(int customerId) {
        try {
            this.adminService.deleteCustomer(customerId);
        } catch (CustomerDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllCustomersTest() {
        System.out.println("All the customers: -> --> --->");
        this.adminService.getAllCustomers().forEach(System.out::println);
    }

    public void getOneCustomerTest(int customerID) {
        try {
            System.out.println("One Customer \n -> --> ---> " + this.adminService.getOneCustomer(customerID));
        } catch (CustomerDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }


}
