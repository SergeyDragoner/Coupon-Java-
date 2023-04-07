package com.example.Coupon_Project.services;

import com.example.Coupon_Project.beans.Category;
import com.example.Coupon_Project.beans.Coupon;
import com.example.Coupon_Project.beans.Customer;
import com.example.Coupon_Project.exceptions.coupons.CouponException;
import com.example.Coupon_Project.exceptions.customers.CustomerDoesntExistException;
import com.example.Coupon_Project.repositories.CompanyRepository;
import com.example.Coupon_Project.repositories.CouponRepository;
import com.example.Coupon_Project.repositories.CustomerRepository;
import com.example.Coupon_Project.services.ClientManager.ClientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServices extends ClientService {

    //For getting the customer ID after the login
    private int customerId = 0;

    public CustomerServices(CompanyRepository companyServices, CustomerRepository customerServices, CouponRepository couponServices) {
        super(companyServices, customerServices, couponServices);
    }

    @Override
    public boolean login(String email, String password) {
        if (customerRepository.existsByEmailAddressAndPassword(email, password))
            customerId = customerRepository.getCustomerByEmailAddressAndPassword(email, password).getId();
        return customerRepository.existsByEmailAddressAndPassword(email, password);
    }


    /**
     * This method is used to purchase a coupon for a customer.
     *
     * @param coupon - The coupon to be purchased.
     * @throws CustomerDoesntExistException - If the customer does not exist in the system.
     * @throws CouponException - If the coupon is not available or has expired, or if it has already been purchased by the customer.
     * @Transactional - Ensures that the method executes within a transactional context.
     */
    @Transactional
    public void purchaseCoupon(Coupon coupon) throws CustomerDoesntExistException, CouponException {
        Date currentDate = new Date(System.currentTimeMillis());
        checkIfCustomerExists(customerId);
        Customer customer = customerRepository.findById(customerId).get();
        if (couponRepository.existsByTitleAndDescriptionAndCompany_Id(coupon.getTitle(), coupon.getDescription(), coupon.getCompanyId())) {
            Coupon couponToPurchase = couponRepository.getByTitleAndDescriptionAndCompany_Id(coupon.getTitle(), coupon.getDescription(), coupon.getCompanyId());
            if (!customer.getCoupons().contains(couponToPurchase)) {
                if (couponToPurchase.getAmount() > 0) {
                    if (couponToPurchase.getEndDate().after(currentDate)) {
                        couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
                        customer.getCoupons().add(couponToPurchase);
                        customerRepository.save(customer);
                    } else {
                        throw new CouponException("The coupon date has -->EXPIRED<--!");
                    }
                } else {
                    throw new CouponException("The coupon is out of -->STOCK<--!");
                }
            } else {
                throw new CouponException("Coupon " + couponToPurchase.getId() + " already purchased by customer: " + customer.getEmailAddress());
            }
        } else {
            throw new CouponException("The coupon doesn't exist");
        }
    }

    /**
     * Retrieves a list of all coupons associated with the customer.
     *
     * @return - A list of coupons associated with the customer.
     * @throws CustomerDoesntExistException - If the customer does not exist.
     */
    public List<Coupon> getCustomerCoupons() throws CustomerDoesntExistException {
        checkIfCustomerExists(customerId);
        return new ArrayList<>(customerRepository.findById(customerId).get().getCoupons());
    }

    /**
     * Retrieves a list of coupons associated with the customer that have the specified category.
     *
     * @param category the category of coupons to retrieve.
     * @return - A list of coupons associated with the customer and the specified category.
     * @throws CustomerDoesntExistException - If the customer does not exist.
     */
    public List<Coupon> getCustomerCouponsByCategory(Category category) throws CustomerDoesntExistException {
        checkIfCustomerExists(customerId);
        return customerRepository.findById(customerId).get().getCoupons()
                .stream()
                .filter(c -> c.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of coupons associated with the customer that have a price less than or equal to the specified maximum price.
     *
     * @param maxPrice - the maximum price of coupons to retrieve.
     * @return - A list of coupons associated with the customer and with a price less than or equal to the specified maximum price.
     * @throws CustomerDoesntExistException - If the customer does not exist.
     */
    public List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) throws CustomerDoesntExistException {
        checkIfCustomerExists(customerId);
        return customerRepository.findById(customerId).get().getCoupons()
                .stream()
                .filter(c -> c.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves details about the customer.
     *
     * @return - The customer object containing details about the customer.
     * @throws CustomerDoesntExistException - If the customer does not exist.
     */
    public Customer getCustomerDetails() {
        return customerRepository.findById(customerId).orElseThrow();
    }

    /**
     * DRY Principle, to throw an exception if the customer don't exist.
     *
     * @param customerId - The customer that logged in.
     * @throws CustomerDoesntExistException - If the customer doesn't exist.
     */
    private void checkIfCustomerExists(int customerId) throws CustomerDoesntExistException {
        if (!customerRepository.existsById(customerId))
            throw new CustomerDoesntExistException();
    }
}
