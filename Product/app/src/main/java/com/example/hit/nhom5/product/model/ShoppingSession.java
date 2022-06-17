package com.example.hit.nhom5.product.model;

import java.util.List;

public class ShoppingSession {
    private Integer shoppingSessionId;
    private Integer total;
    private List<Cart> carts;

    public ShoppingSession() { }

    public ShoppingSession(Integer shoppingSessionId, Integer total, List<Cart> carts) {
        this.shoppingSessionId = shoppingSessionId;
        this.total = total;
        this.carts = carts;
    }

    public Integer getShoppingSessionId() {
        return shoppingSessionId;
    }

    public void setShoppingSessionId(Integer shoppingSessionId) {
        this.shoppingSessionId = shoppingSessionId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "ShoppingSession{" +
                "shoppingSessionId=" + shoppingSessionId +
                ", total=" + total +
                ", carts=" + carts +
                '}';
    }
}
