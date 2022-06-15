package com.example.hit.nhom5.product.model;

public class Cart {
    private Integer soLuong;
    private Product product;

    public Cart() { }

    public Cart(Integer soLuong, Product product) {
        this.soLuong = soLuong;
        this.product = product;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
