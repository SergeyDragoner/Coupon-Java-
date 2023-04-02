package com.example.Coupon_Project.exceptions.customers;

public class CustomerDoesntExistException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CustomerDoesntExistException() {
        super("Customer does not exist in the database!");
    }
}
