package com.example.Coupon_Project.exceptions.companies;

public class CompanyNotAllowedToBeChangedException extends Exception {
    public CompanyNotAllowedToBeChangedException() {
        super("Company name is not allowed to be changed!");
    }
}
