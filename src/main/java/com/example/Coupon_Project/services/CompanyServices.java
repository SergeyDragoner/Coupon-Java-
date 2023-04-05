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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServices extends ClientService {

    private int companyId = 0;

    public int getCompanyId(){
        return companyId;
    }

    public CompanyServices(CompanyRepository companyServices, CustomerRepository customerServices, CouponRepository couponServices) {
        super(companyServices, customerServices, couponServices);
    }

    @Override
    public boolean login(String email, String password) {
        if(companyRepository.existsByEmailAddressAndPassword(email, password))
            companyId = companyRepository.getCompanyByEmailAddressAndPassword(email,password).getId();
        return companyRepository.existsByEmailAddressAndPassword(email, password);
    }


    // TODO check if you need to pass companyId in each companyService methods
    /**
     * Add a new coupon for the company
     *
     * @param coupon Coupon to be added
     * @throws CouponException if a coupon with the same title already exists for the same company
     */
    public void addCoupon(Coupon coupon) throws CouponException {
        ArrayList<Coupon> companyCoupons = (ArrayList<Coupon>) couponRepository.findAll();
        boolean flag = true;
        for (Coupon couponCoupon : companyCoupons) {
            if (couponCoupon.getTitle().equalsIgnoreCase(coupon.getTitle()) && couponCoupon.getCompanyId() == coupon.getCompanyId()) {
                flag = false;
                break;
            }
        }
        if (flag)
            couponRepository.save(coupon);
        else
            throw new CouponException("Cannot add a coupon with the same title to the same company");
    }



    /**
     * Update an existing coupon of the company
     *
     * @param coupon Coupon to be updated
     * @throws CouponException - if the coupon ID or company ID is attempted to be changed, or the company ID of the coupon being updated is different from the company's ID
     */
    public void updateCoupon(Coupon coupon) throws CouponException {
        //Can't update coupon ID & Company ID
        if (couponRepository.existsById(coupon.getId())) {
            Coupon couponToCheck = couponRepository.findById(coupon.getId()).orElseThrow();
            if (couponToCheck.getCompanyId() == coupon.getCompanyId()) {
                couponRepository.save(coupon);
            } else {
                throw new CouponException("Cannot update the company ID!");
            }
        } else
            throw new CouponException("You cannot update the Coupon ID or The coupon Do not exist!");
    }



    /**
     * Delete a coupon of the company and the history of the buyers(Customers).
     *
     * @param couponId - ID of the coupon to be deleted.
     * @throws CouponException - if the coupon doesn't exist.
     */
    public void deleteCoupon(int couponId) throws CouponException {
        if (couponRepository.existsById(couponId)) {
            ArrayList<Customer> customersToDelete = (ArrayList<Customer>) customerRepository.findAllByCouponsId(couponId);
            System.out.println(customersToDelete);
            if (customersToDelete.size() > 0) {
                for (Customer customer : customersToDelete) {
                    customerRepository.deleteById(customer.getId());
                }
            }
            couponRepository.deleteById(couponId);
        } else
            throw new CouponException("Sorry The coupon doesn't exist!");
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
        ArrayList<Coupon> companyCoupons = (ArrayList<Coupon>) couponRepository.getCouponsByCompany_Id(companyId);
        ArrayList<Coupon> companyCouponsByCategory = new ArrayList<>();
        for (Coupon companyCoupon : companyCoupons){
            if(companyCoupon.getCategory().equals(category))
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
        List<Coupon> companyCoupons = couponRepository.getCouponsByCompany_Id(companyId);
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
