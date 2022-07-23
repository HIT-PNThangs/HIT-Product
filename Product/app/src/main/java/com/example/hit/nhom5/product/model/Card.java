package com.example.hit.nhom5.product.model;

public class Card {
    private int resourceId;
    private String title;
    private String address;
    private String price;
    private int numberFood;

    public Card(int resourceId, String title, String address, String price, int numberFood) {
        this.resourceId = resourceId;
        this.title = title;
        this.address = address;
        this.price = price;
        this.numberFood = numberFood;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumberFood() {
        return numberFood;
    }

    public void setNumberFood(int numberFood) {
        this.numberFood = numberFood;
    }
}
