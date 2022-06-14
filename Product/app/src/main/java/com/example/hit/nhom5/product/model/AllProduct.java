package com.example.hit.nhom5.product.model;

import java.util.List;

public class AllProduct {
    private List<Product> data;

    public AllProduct() {
    }

    public AllProduct(List<Product> data) {
        this.data = data;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
