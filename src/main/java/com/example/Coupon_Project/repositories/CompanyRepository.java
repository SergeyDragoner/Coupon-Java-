package com.example.Coupon_Project.repositories;

import com.example.Coupon_Project.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    /**
     * Returns true if the given Company emailAddress and Password is in the DB.
     * @param emailAddress - The email address to check.
     * @param password - The password to check.
     * @return - Boolean indicating whether the Company emailAddress and Password is in the DB.
     */
    boolean existsByEmailAddressAndPassword(String emailAddress, String password);

    /**
     * In order to add a new company these fields must not be in the DB.
     * @param emailAddress - The email address to check.
     * @param name - The name of the company to check.
     * @return - true if there is already a Company with that name or email.
     */
    boolean existsByEmailAddressAndName(String emailAddress, String name);

    boolean existsByEmailAddress(String emailAddress);
    boolean existsByName(String name);

}
