package com.mabaya.test.sagi.demo.campaign;


import com.mabaya.test.sagi.demo.product.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Campaign {
//INSERT into user VALUES(sysdate(), 'food', 0.5, 'Grand')
    private Date startDate;

    private String category;

    private double bid;

    @Id
    private String name;

    @ManyToMany
    @JoinTable(
            name = "campaign_product",
            joinColumns = @JoinColumn(name = "campaign_name"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Campaign() {
    }

    public Campaign(Date startDate, String category, double bid, String name) {
        this.startDate = startDate;
        this.category = category;
        this.bid = bid;
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isActive() {
        return new Date(startDate.getTime() + 864000000).after(new Date()); // check if 10 days passed
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
