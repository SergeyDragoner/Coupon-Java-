package com.example.Coupon_Project.services;

import com.example.Coupon_Project.beans.Category;
import com.example.Coupon_Project.beans.Company;
import com.example.Coupon_Project.beans.Coupon;
import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.exceptions.companies.CompanyDoesntExistException;
import com.example.Coupon_Project.exceptions.coupons.CouponException;
import com.example.Coupon_Project.repositories.CompanyRepository;
import com.example.Coupon_Project.repositories.CouponRepository;
import com.example.Coupon_Project.repositories.CustomerRepository;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope("prototype")
public class CompanyServices extends ClientService {

    //For getting the company ID after the login
    private int companyId = 0;

    public int getCompanyId() {
        return companyId;
    }

    public CompanyServices(CompanyRepository companyServices, CustomerRepository customerServices, CouponRepository couponServices) {
        super(companyServices, customerServices, couponServices);
    }

    @Override
    public boolean login(String email, String password) {
        if (companyRepository.existsByEmailAddressAndPassword(email, password))
            companyId = companyRepository.getCompanyByEmailAddressAndPassword(email, password).getId();
        return companyRepository.existsByEmailAddressAndPassword(email, password);
    }


    // TODO check if you need to pass companyId in each companyService methods

    /**
     * Adds a new coupon to the system.
     *
     * @param coupon the coupon to add
     * @throws CouponException if a coupon with the same title already exists for the same company
     */
    public void addCoupon(Coupon coupon) throws CouponException {
        // Check if a coupon with the same title and company already exists
        boolean exists = couponRepository.existsByTitleAndCompany_Id(coupon.getTitle(), coupon.getCompanyId());
        if (exists) {
            throw new CouponException("Cannot add a coupon with the same title to the same company");
        }
        if (new Date(System.currentTimeMillis()).before(coupon.getEndDate())) {
            // Save the new coupon
            couponRepository.save(coupon);
            System.out.println("Coupon " + coupon.getTitle()+" added to the DB for the company with id: " + companyId);
        } else
            throw new CouponException("Cannot add a Coupon with expired date");
    }


    /**
     * Update an existing coupon of the company
     *
     * @param coupon Coupon to be updated
     * @throws CouponException - if the coupon ID or company ID is attempted to be changed, or the company ID of the coupon being updated is different from the company's ID
     */
    public void updateCoupon(Coupon coupon) throws CouponException {
        // Can't update coupon ID & Company ID
        Optional<Coupon> couponOptional = couponRepository.findById(coupon.getId());
        if (couponOptional.isPresent()) {
            Coupon couponFromDB = couponOptional.get();
            if (couponFromDB.getCompanyId() == companyId) {
                couponRepository.save(coupon);
                System.out.println("Coupon updated successfully!");
            } else {
                throw new CouponException("Cannot update the coupon ID or company ID!");
            }
        } else {
            throw new CouponException("Coupon does not exist!");
        }
    }


    /**
     * Delete a coupon of the company and the history of the buyers (customers).
     *
     * @param couponId - ID of the coupon to be deleted.
     * @throws CouponException - if the coupon doesn't exist.
     */
    public void deleteCoupon(int couponId) throws CouponException {
        // Check if the coupon exists in the DB
        Optional<Coupon> couponOptional = couponRepository.findById(couponId);
        if (couponOptional.isPresent()) {
            Coupon coupon = couponOptional.get();
            // Find all customers who have the coupon in their coupon list
            List<Customer> customers = customerRepository.findAllByCouponsId(couponId);
            // Remove the coupon from each customer's coupon list and save the updated customer
            customers.forEach(customer -> {
                customer.getCoupons().remove(coupon);
                customerRepository.save(customer);
            });
            // Delete the coupon from the coupon repository
            couponRepository.deleteById(coupon.getId());
        } else {
            // Throw a CouponException if the coupon doesn't exist
            throw new CouponException("The coupon with ID " + couponId + " doesn't exist.");
        }
        System.out.println("Successfully deleted the coupon with ID: " + couponId);
    }


    /**
     * Get all coupons of the company
     *
     * @return list of all coupons of the company
     */
    public List<Coupon> getCompanyCoupons() {
        return couponRepository.getCouponsByCompany_Id(companyId);
    }


    /**
     * Get all coupons of the company with the specified category
     *
     * @param category - the category of the coupons to be retrieved
     * @return - list of coupons of the company with the specified category
     */
    public List<Coupon> getCompanyCouponsByCategory(Category category) {
        ArrayList<Coupon> companyCoupons = (ArrayList<Coupon>) getCompanyCoupons();
        ArrayList<Coupon> companyCouponsByCategory = new ArrayList<>();
        for (Coupon companyCoupon : companyCoupons) {
            if (companyCoupon.getCategory().equals(category))
                companyCouponsByCategory.add(companyCoupon);
        }
        return companyCouponsByCategory;
    }


    /**
     * Get all coupons of the company with price up to the specified maximum price
     *
     * @param maxPrice the maximum price of the coupons to be retrieved
     * @return list of coupons of the company with price up to the specified maximum price
     */
    public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) {
        List<Coupon> companyCoupons = getCompanyCoupons();
        List<Coupon> companyCouponsByMaxPrice = new ArrayList<>();
        for (Coupon companyCoupon : companyCoupons) {
            if (companyCoupon.getPrice() <= maxPrice)
                companyCouponsByMaxPrice.add(companyCoupon);
        }
        return companyCouponsByMaxPrice;
    }


    /**
     * Returns all the details of the company.
     *
     * @return the details of the company.
     * @throws CompanyDoesntExistException if the company with the given ID doesn't exist
     */
    public Company getCompanyDetails() throws CompanyDoesntExistException {
        return companyRepository.findById(companyId).orElseThrow(CompanyDoesntExistException::new);
    }
}
