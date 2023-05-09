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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public class CustomerServices extends ClientService {

    //For getting the customer ID after the login
    private int customerId = 0;

    public CustomerServices(CompanyRepository companyServices, CustomerRepository customerServices, CouponRepository couponServices) {
        super(companyServices, customerServices, couponServices);
    }

    @Override
    public boolean login(String email, String password) {
        Customer customer = customerRepository.getCustomerByEmailAddressAndPassword(email, password);
        if (customer != null) {
            customerId = customer.getId();
            return true;
        }
        return false;
    }

    public int getCustomerId() {
        return customerId;
    }


    /**
     * This method is used to purchase a coupon for a customer.
     *
     * @param coupon - The coupon to be purchased.
     * @throws CustomerDoesntExistException - If the customer does not exist in the system.
     * @throws CouponException              - If the coupon is not available or has expired, or if it has already been purchased by the customer.
     * @Transactional - Ensures that the method executes within a transactional context.
     */
    @Transactional
    public void purchaseCouponForCustomer(Coupon coupon) throws CustomerDoesntExistException, CouponException {
        checkIfCustomerExists(customerId);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            Coupon couponToPurchase = couponRepository.getByTitleAndDescriptionAndCompany_Id(coupon.getTitle(), coupon.getDescription(), coupon.getCompanyId());
            if (couponToPurchase == null) {
                throw new CouponException("The coupon doesn't exist");
            }
            if (customer.getCoupons().contains(couponToPurchase)) {
                throw new CouponException(String.format("Coupon %d already purchased by customer: %s", couponToPurchase.getId(), customer.getEmailAddress()));
            }
            if (couponToPurchase.getAmount() <= 0) {
                throw new CouponException("The coupon is out of stock");
            }
            if (couponToPurchase.getEndDate().before(new Date(System.currentTimeMillis()))) {
                System.out.println(couponToPurchase.getEndDate());
                throw new CouponException("You cannot purchase the coupon because, the coupon date has expired");
            }
            couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
            customer.getCoupons().add(couponToPurchase);
            customerRepository.save(customer);
            System.out.println("Coupon '" + coupon.getTitle() + "' purchased successfully");
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
    public Customer getCustomerDetails() throws CustomerDoesntExistException {
        return customerRepository.findById(customerId).orElseThrow(CustomerDoesntExistException::new);
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
