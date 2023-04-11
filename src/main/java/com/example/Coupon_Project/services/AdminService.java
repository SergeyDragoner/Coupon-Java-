package com.example.Coupon_Project.services;

import com.example.Coupon_Project.beans.Company;
import com.example.Coupon_Project.beans.Coupon;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("prototype")
public class AdminService extends ClientService {

    public AdminService(CompanyRepository companyServices, CustomerRepository customerServices, CouponRepository couponServices) {
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
        return email.equalsIgnoreCase("admin@admin.com") && password.equals("admin");
    }

    /**
     * This method is to add a company to the DB.
     *
     * @param company - The company to be added.
     * @throws CompanyAlreadyExistException - If company already exists.
     */
    public void addCompany(Company company) throws CompanyAlreadyExistException {
        //Check if company name and email doesn't already exist.
        if (companyRepository.existsByEmailAddress(company.getEmailAddress())) {
            throw new CompanyAlreadyExistException();
        }
        if (companyRepository.existsByName(company.getName())) {
            throw new CompanyAlreadyExistException();
        }
        companyRepository.save(company);
        System.out.println("Company " + company.getName() + " with the email: " + company.getEmailAddress() + ", added successfully ");

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
        Optional<Company> companyOptional = companyRepository.findById(company.getId());
        if (companyOptional.isPresent()) {
            Company companyFromDB = companyOptional.get();
            if (company.getName().equals(companyFromDB.getName())) {
                companyRepository.save(company);
                System.out.println("Company " + companyFromDB.getName() + ", updated successfully");
            } else
                throw new CompanyNotAllowedToBeChangedException();
        } else
            throw new CompanyDoesntExistException();

    }


    /**
     * Deletes a company and all associated coupons and coupon purchases from the system.
     *
     * @param companyId the ID of the company to delete
     * @throws CompanyDoesntExistException if no company with the specified ID exists
     */
    public void deleteCompany(int companyId) throws CompanyDoesntExistException {
        //Delete the company and the coupon's history!
        if (!companyRepository.existsById(companyId))
            throw new CompanyDoesntExistException();

        List<Coupon> companyCoupons = couponRepository.getCouponsByCompany_Id(companyId);
        List<Customer> allCustomers = customerRepository.findAll();
        for (Customer customer : allCustomers) {
            List<Coupon> customerCoupons = customer.getCoupons();
            int i = 0;
            while (i < customerCoupons.size()) {
                Coupon coupon = customerCoupons.get(i);
                if (coupon.getCompanyId() == companyId)
                    customerCoupons.remove(i);
                else
                    i++;

            }
            customerRepository.save(customer);
        }

        for (Coupon coupon : companyCoupons) {
            couponRepository.deleteById(coupon.getId());
        }
        companyRepository.deleteById(companyId);
        System.out.println("Company with companyId: " + companyId + ", deleted successfully");
    }

    /**
     * This method is to get all the companies that are currently in the DB.
     *
     * @return - Empty array of Companies or with values.
     */
    public List<Company> getAllCompanies() {
        return companyRepository.findAll(); //This will return an empty array or full.
    }

    /**
     * Returns a Company object from the DB if exists!
     *
     * @param companyId - The company id.
     * @return - Company
     * @throws CompanyDoesntExistException - If company is not in the DB throw an exception.
     */
    public Company getOneCompany(int companyId) throws CompanyDoesntExistException {
//        if (companyRepository.existsById(companyId))
        return companyRepository.findById(companyId).orElseThrow(CompanyDoesntExistException::new);
//        return null; //This will never be reached!
    }

    //Customer -> --> --->

    /**
     * This method adds Customer to the DB, only if the customer email is not already exist!.
     *
     * @param customer - The Customer to add to the DB.
     * @throws CustomerAlreadyExistException - If the Customer email is already in the DB.
     */
    public void addCustomer(Customer customer) throws CustomerAlreadyExistException {
        //Add only if the customer email is not already exist!
        if (!customerRepository.existsCustomerByEmailAddress(customer.getEmailAddress())) {
            customerRepository.save(customer);
            System.out.println("Added Customer");
        } else
            throw new CustomerAlreadyExistException();
    }

    /**
     * This method updates the Customer in the DB, only if the customer id is the same.
     *
     * @param customer - The Customer you want to update.
     * @throws CustomerDoesntExistException - If the customer ID is not found in the DB.
     */
    public void updateCustomer(Customer customer) throws CustomerDoesntExistException {
        //Cant update the customer ID.
        if (!customerRepository.existsById(customer.getId()))
            throw new CustomerDoesntExistException();

        customerRepository.save(customer);

    }

    /**
     * Deletes a customer from the DB by the given customer ID.
     *
     * @param customerId - the ID of the customer to delete
     * @throws CustomerDoesntExistException - if the customer does not exist in the system
     */
    public void deleteCustomer(int customerId) throws CustomerDoesntExistException {
        if (!customerRepository.existsById(customerId))
            throw new CustomerDoesntExistException();
        customerRepository.deleteById(customerId);
        System.out.println("Customer with ID " + customerId + ", deleted successfully");
    }

    /**
     * This method returns a List of all the customers from the DB, or an Empty list.
     *
     * @return - List of customers.
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * This method is to get a Customer from the DB, if he/she exists.
     *
     * @param customerId - The ID of the Customer you want to get.
     * @return - The Customer.
     * @throws CustomerDoesntExistException - If Customer is not in the DB!
     */
    public Customer getOneCustomer(int customerId) throws CustomerDoesntExistException {
        return customerRepository.findById(customerId).orElseThrow(CustomerDoesntExistException::new);
    }

}
