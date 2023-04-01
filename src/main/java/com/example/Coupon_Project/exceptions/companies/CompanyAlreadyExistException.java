package com.example.Coupon_Project.exceptions.companies;

public class CompanyAlreadyExistException extends Exception {

    public CompanyAlreadyExistException() {
        super("The company already exists, please try again with different email and company name");
    }
}
