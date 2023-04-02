package com.example.Coupon_Project.services;

import com.example.Coupon_Project.beans.Company;
import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.exceptions.companies.CompanyAlreadyExistException;
import com.example.Coupon_Project.exceptions.companies.CompanyDoesntExistException;
import com.example.Coupon_Project.exceptions.companies.CompanyNotAllowedToBeChangedException;
import com.example.Coupon_Project.exceptions.customers.CustomerAlreadyExistException;
import com.example.Coupon_Project.services.ClientManager.ClientService;

import java.util.List;

public class Administrator extends ClientService {

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
            if (!company.getName().equals(com.getName()) && company.getEmailAddress().equals(com.getEmailAddress())) {
                companyServices.save(company);
                return;
            } else
                break;
        }
        throw new CompanyAlreadyExistException();
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

    }

    public List<Company> getAllCompanies() {
        return companyServices.findAll(); //This will return an empty array or full.
    }

    public Company getOneCompany(int companyId) throws CompanyDoesntExistException {
        if (companyServices.existsById(companyId))
            return companyServices.findById(companyId).orElseThrow(CompanyDoesntExistException::new);
        return null; //This will never be reached!
    }

    //Customer -> --> --->
    public void addCustomer(Customer customer) throws CustomerAlreadyExistException {
        //Add only if the customer email is not already exist!
        if(!customerServices.findCustomerByEmailAddress(customer.getEmailAddress()))
            customerServices.save(customer);
        else
            throw new CustomerAlreadyExistException();
    }


}
