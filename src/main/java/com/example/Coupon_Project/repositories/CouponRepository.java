package com.example.Coupon_Project.repositories;

import com.example.Coupon_Project.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    /**
     * Returns a list of all coupons that belong to a specific company ID.
     *
     * @param companyId the ID of the company to retrieve coupons for
     * @return a list of coupons belonging to the specified company ID
     */
    List<Coupon> getCouponsByCompany_Id(int companyId);

    /**
     * Retrieves a coupon with the specified title, description, and company ID.
     *
     * @param title the title of the coupon to retrieve
     * @param description the description of the coupon to retrieve
     * @param companyId the ID of the company the coupon belongs to
     * @return the coupon with the specified title, description, and company ID
     */
    Coupon getByTitleAndDescriptionAndCompany_Id(String title, String description, int companyId);

    /**
     * Returns whether a coupon with the specified title and company ID exists in the system, ignoring case.
     *
     * @param title - The title of the coupon to check for
     * @param companyId - The ID of the company associated with the coupon to check for
     * @return - true if a coupon with the specified title and company ID exists in the system, ignoring case; false otherwise
     */
    boolean existsByTitleAndCompany_Id(String title, int companyId);



}
