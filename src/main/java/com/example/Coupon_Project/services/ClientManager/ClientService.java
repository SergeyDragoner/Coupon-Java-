package com.example.Coupon_Project.services.ClientManager;

import com.example.Coupon_Project.repositories.CompanyRepository;
import com.example.Coupon_Project.repositories.CouponRepository;
import com.example.Coupon_Project.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {

//    @Autowired
    protected CompanyRepository companyServices;

//    @Autowired
    protected CustomerRepository customerServices;

//    @Autowired
    protected CouponRepository couponServices;

    public ClientService(CompanyRepository companyServices, CustomerRepository customerServices, CouponRepository couponServices) {
        this.companyServices = companyServices;
        this.customerServices = customerServices;
        this.couponServices = couponServices;
    }

    public abstract boolean login(String email, String password);

}
