package com.example.Coupon_Project.beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId", foreignKey = @ForeignKey(name = "FK_companyId_CouponId"))
    private Company company = new Company();
    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private Date startDate;
    private Date endDate;
    @NotNull
    private int amount;
    @NotNull
    private double price;
    private String image;

    public Coupon() {
    }

    public Coupon(int companyId, Category category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
        this.company.setId(companyId);
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getCompanyId() {
        return company.getId();
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coupon's id: " + id +
//                ", Company Id: " + companyId +
                ", Category: " + category +
                ", Title: " + title +
                ", Description: " + description +
                ", Start Date: " + startDate.toString() +
                ", End Date: " + endDate.toString() +
                ", Amount: " + amount +
                ", Price: " + price +
                ", Image: " + image;
    }
}
