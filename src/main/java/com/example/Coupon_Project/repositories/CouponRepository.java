package com.example.Coupon_Project.repositories;

import com.example.Coupon_Project.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> getCouponsByCompany_Id(int companyId);

    boolean existsByTitleAndDescriptionAndCompany_Id(@NotNull String title, String description, int company_id);

    Coupon getByTitleAndDescriptionAndCompany_Id(String title, String description, int companyId);

}
