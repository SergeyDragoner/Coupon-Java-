package com.example.Coupon_Project.repositories;

import com.example.Coupon_Project.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Returns true if the given customer emailAddress is in the DB.
     * @param emailAddress - The email address to check.
     * @return - Boolean indicating whether the customer emailAddress is in the DB.
     */
    boolean existsCustomerByEmailAddress(String emailAddress);


    /**
     * Returns true if the given customer emailAddress and Password is in the DB.
     * @param emailAddress - The email address to check.
     * @param password - The password to check.
     * @return - Boolean indicating whether the customer emailAddress and Password is in the DB.
     */
    boolean existsByEmailAddressAndPassword(String emailAddress, String password);

//    boolean existsByCouponsId(int coupons_id);
}
