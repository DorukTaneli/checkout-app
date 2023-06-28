package com.example.checkout.offer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Offer {
    @Id
    private Character sku;
    private Integer count;
    private Integer discount;

    public Offer() {
    }

    public Offer(Character sku, Integer count, Integer discount) {
        this.sku = sku;
        this.count = count;
        this.discount = discount;
    }

    public Character getSku() {
        return sku;
    }

    public void setSku(Character sku) {
        this.sku = sku;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
