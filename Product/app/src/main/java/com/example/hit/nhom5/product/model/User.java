package com.example.hit.nhom5.product.model;

public class User {
    private Integer userId;
    private String avt;
    private String firstName, lastName;
    private String address;
    private String telephone;
    private Boolean status;
    private Role roles;
    private ShoppingSession shoppingSession;

    public User() { }

    public User(Integer userId, String avt, String firstName, String lastName,
                String address, String telephone, Boolean status, Role roles,
                ShoppingSession shoppingSession) {
        this.userId = userId;
        this.avt = avt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.status = status;
        this.roles = roles;
        this.shoppingSession = shoppingSession;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public ShoppingSession getShoppingSession() {
        return shoppingSession;
    }

    public void setShoppingSession(ShoppingSession shoppingSession) {
        this.shoppingSession = shoppingSession;
    }
}
