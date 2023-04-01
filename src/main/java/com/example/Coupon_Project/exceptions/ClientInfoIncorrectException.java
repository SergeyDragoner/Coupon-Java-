package com.example.Coupon_Project.exceptions;

public class ClientInfoIncorrectException extends Exception {

    public ClientInfoIncorrectException() {
        super("Sorry the email or password is incorrect, please try again!");
    }
}
