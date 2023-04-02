package com.example.Coupon_Project.services;

import com.example.Coupon_Project.beans.Company;
import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.exceptions.companies.CompanyAlreadyExistException;
import com.example.Coupon_Project.exceptions.companies.CompanyDoesntExistException;
import com.example.Coupon_Project.exceptions.companies.CompanyNotAllowedToBeChangedException;
import com.example.Coupon_Project.exceptions.customers.CustomerAlreadyExistException;
import com.example.Coupon_Project.exceptions.customers.CustomerDoesntExistException;
import com.example.Coupon_Project.repositories.CompanyRepository;
import com.example.Coupon_Project.repositories.CouponRepository;
import com.example.Coupon_Project.repositories.CustomerRepository;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Administrator extends ClientService {

    public Administrator(CompanyRepository companyServices, CustomerRepository customerServices, CouponRepository couponServices) {
        super(companyServices, customerServices, couponServices);
    }

    /**
     * This is an abstracted method from ClientService.
     * Used in LoginManager in order to get access to all off the Administrator
     * methods.
     *
     * @param email    - The email is needed to be hard coded written.
     * @param password - The password is needed to be hard coded written.
     * @return - Boolean for the LoginManager.
     */
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    /**
     * This method is to add a company to the DB.
     *
     * @param company - The company to be added.
     * @throws CompanyAlreadyExistException - If company already exists.
     */
    public void addCompany(Company company) throws CompanyAlreadyExistException {
        //Check if company name and email doesn't already exist.
        List<Company> companies = companyServices.findAll();
        for (Company com : companies) {
            if (company.getName().equals(com.getName()) && company.getEmailAddress().equals(com.getEmailAddress()))
                throw new CompanyAlreadyExistException();
        }
        companyServices.save(company);

    }

    /**
     * This method is updating an existing company, but only if the ID is the same
     * and the company name!
     *
     * @param company - The company to update.
     * @throws CompanyDoesntExistException           - If the company does not exist while looking for him in the DB!
     * @throws CompanyNotAllowedToBeChangedException - If the company name is not the same as in the DB.
     */
    public void updateCompany(Company company) throws CompanyDoesntExistException, CompanyNotAllowedToBeChangedException {
        //Cant update the name or ID!
        Company com = companyServices.findById(company.getId())
                .orElseThrow(() -> new CompanyDoesntExistException());
        if (company.getName().equals(com.getName()))
            companyServices.save(company);
        else
            throw new CompanyNotAllowedToBeChangedException();
    }


    public void deleteCompany(int companyId) {
        companyServices.deleteById(companyId);
    }

    /**
     * This method is to get all the companies that are currently in the DB.
     * @return - Empty array of Companies or with values.
     */
    public List<Company> getAllCompanies() {
        return companyServices.findAll(); //This will return an empty array or full.
    }

    /**
     * Returns a Company object from the DB if exists!
     * @param companyId - The company id.
     * @return - Company
     * @throws CompanyDoesntExistException - If company is not in the DB throw an exception.
     */
    public Company getOneCompany(int companyId) throws CompanyDoesntExistException {
        if (companyServices.existsById(companyId))
            return companyServices.findById(companyId).orElseThrow(CompanyDoesntExistException::new);
        return null; //This will never be reached!
    }

    //Customer -> --> --->

    /**
     * This method adds Customer to the DB, only if the customer email is not already exist!.
     * @param customer - The Customer to add to the DB.
     * @throws CustomerAlreadyExistException - If the Customer email is already in the DB.
     */
    public void addCustomer(Customer customer) throws CustomerAlreadyExistException {
        //Add only if the customer email is not already exist!
        if (!customerServices.existsCustomerByEmailAddress(customer.getEmailAddress())) {
            customerServices.save(customer);
            System.out.println("Added Customer");
        }else
            throw new CustomerAlreadyExistException();
    }

    /**
     * This method updates the Customer in the DB, only if the customer id is the same.
     * @param customer - The Customer you want to update.
     * @throws CustomerDoesntExistException - If the customer ID is not found in the DB.
     */
    public void updateCustomer(Customer customer) throws CustomerDoesntExistException {
        //Cant update the customer ID.
        if(customerServices.existsById(customer.getId())) {
            customerServices.save(customer);
            return;
        }
        throw new CustomerDoesntExistException();

    }

    public void deleteCustomer(int customerId){
        //customerServices.deleteById(customerId);
    }

    /**
     * This method returns a List of all the customers from the DB, or an Empty list.
     * @return - List of customers.
     */
    public List<Customer> getAllCustomers(){
       return customerServices.findAll();

    }

    /**
     * This method is to get a Customer from the DB, if he/she exists.
     * @param customerId - The ID of the Customer you want to get.
     * @return - The Customer.
     * @throws CustomerDoesntExistException - If Customer is not in the DB!
     */
    public Customer getOneCustomer(int customerId) throws CustomerDoesntExistException {
        return customerServices.findById(customerId).orElseThrow(CustomerDoesntExistException::new);
    }

}
