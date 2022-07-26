package com.example.hit.nhom5.product.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User implements Parcelable {
    private Integer userId;
    private String avt;
    private String name;
    private String email;
    private String address;
    private String telephone;
    private Boolean status;
    private List<Role> roles;
    private List<Cart> carts;
    private String uid;

    public User() { }

    public User(Integer userId, String avt, String name, String email, String address, String telephone,
                Boolean status, List<Role> roles, List<Cart> carts, String uid) {
        this.userId = userId;
        this.avt = avt;
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.status = status;
        this.roles = roles;
        this.carts = carts;
        this.uid = uid;
    }

    protected User(Parcel in) {
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        avt = in.readString();
        name = in.readString();
        email = in.readString();
        address = in.readString();
        telephone = in.readString();
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
        uid = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> ans = new HashMap<>();

        ans.put("userId", userId);
        ans.put("avt", avt);
        ans.put("name", name);
        ans.put("email", email);
        ans.put("address", address);
        ans.put("status", status);
        ans.put("roles", roles);
        ans.put("carts", carts);
        ans.put("uid", uid);

        return ans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (userId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(userId);
        }
        parcel.writeString(avt);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(address);
        parcel.writeString(telephone);
        parcel.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
        parcel.writeString(uid);
    }
}
