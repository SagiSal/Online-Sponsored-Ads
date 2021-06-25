package com.mabaya.test.sagi.demo.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mabaya.test.sagi.demo.campaign.Campaign;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Product {

    @Id
    private Integer serialNumber;

    private String title;

    private String category;

    private double price;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Campaign> campaigns;

    @Override
    public String toString() {
        return "Product{" +
                "serialNumber=" + serialNumber +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }

    public Product(Integer serialNumber, String title, String category, double price) {
        this.serialNumber = serialNumber;
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public Product() {
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
