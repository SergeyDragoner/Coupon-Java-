package com.example.Coupon_Project.repositories;

import com.example.Coupon_Project.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean findCustomerByEmailAddress(String emailAddress);
}
