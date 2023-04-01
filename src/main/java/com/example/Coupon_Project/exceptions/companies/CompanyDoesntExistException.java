package com.example.Coupon_Project.exceptions.companies;

public class CompanyDoesntExistException extends Exception {

    public CompanyDoesntExistException() {
        super("The company does not exist!");
    }
}
