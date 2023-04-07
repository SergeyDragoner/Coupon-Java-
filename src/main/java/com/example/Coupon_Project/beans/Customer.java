package com.example.Coupon_Project.beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(length = 15)
    private String firstName;
    @Column(length = 35)
    private String lastName;
    @NotNull
    private String emailAddress;
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "customers_coupons",
            joinColumns = @JoinColumn(name = "customersId", foreignKey = @ForeignKey(name = "FK_couponId_customerId")),
            inverseJoinColumns = @JoinColumn(name = "couponsId", foreignKey = @ForeignKey(name = "FK_customerId_couponId")))
    private List<Coupon> coupons;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        coupons = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    @Override
    public String toString() {
        return "Customer's Id: " + id +
                ", First Name: " + firstName +
                ", Last Name: " + lastName +
                ", Email: " + emailAddress +
                ", Password: " + password +
                ", Coupons: " + (coupons.size() > 0 ? coupons : "0");
    }
}
