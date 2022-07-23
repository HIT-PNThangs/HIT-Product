package com.example.hit.nhom5.product.model;

public class Delivered {
    private int resoureId;
    private String typeFood;
    private String name;
    private String address;
    private String price;

    public Delivered(int resoureId, String typeFood, String name, String address, String price) {
        this.resoureId = resoureId;
        this.typeFood = typeFood;
        this.name = name;
        this.address = address;
        this.price = price;
    }

    public int getResoureId() {
        return resoureId;
    }

    public void setResoureId(int resoureId) {
        this.resoureId = resoureId;
    }

    public String getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(String typeFood) {
        this.typeFood = typeFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
