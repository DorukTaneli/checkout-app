package com.example.checkout.pricing;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Pricing {
    @Id
    private Character sku;
    private Integer price;

    public Pricing() {
    }

    public Pricing(Character sku, Integer price) {
        this.sku = sku;
        this.price = price;
    }

    public Character getSku() {
        return sku;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "sku=" + sku +
                ", price=" + price +
                '}';
    }
}
