package com.example.Coupon_Project.exceptions.customers;

public class CustomerAlreadyExistException extends Exception {
    public CustomerAlreadyExistException() {
        super("Customer is already exists in the database!");
    }
}
