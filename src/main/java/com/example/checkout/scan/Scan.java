package com.example.checkout.scan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Scan {
    @Id
    private Character sku;
    private Integer count;

    public Scan(){
    }

    public Scan(Character sku, Integer count) {
        this.sku = sku;
        this.count = count;
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
}
