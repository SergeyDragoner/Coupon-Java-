package com.example.Coupon_Project.repositories;

import com.example.Coupon_Project.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Returns true if the given customer emailAddress is in the DB.
     *
     * @param emailAddress - The email address to check.
     * @return - Boolean indicating whether the customer emailAddress is in the DB.
     */
    boolean existsCustomerByEmailAddress(String emailAddress);


    /**
     * Returns true if the given customer emailAddress and Password is in the DB.
     *
     * @param emailAddress - The email address to check.
     * @param password - The password to check.
     * @return - Boolean indicating whether the customer emailAddress and Password is in the DB.
     */
    boolean existsByEmailAddressAndPassword(String emailAddress, String password);

    /**
     * Finds all customers who have purchased the coupon with the given ID.
     *
     * @param coupons the ID of the coupon to search for
     * @return a list of all customers who have purchased the coupon with the given ID
     */
    List<Customer> findAllByCouponsId(int coupons); // Check this method after the customer bought!

    /**
     * Finds a customer with the given email address and password.
     *
     * @param emailAddress the email address of the customer to search for.
     * @param password the password of the customer to search for.
     * @return the customer with the given email address and password, or null if not found.
     */
    Customer getCustomerByEmailAddressAndPassword(String emailAddress, String password);
}
