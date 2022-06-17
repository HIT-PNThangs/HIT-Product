package com.example.hit.nhom5.product.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Product implements Parcelable {
    private Integer productID;
    private String image;
    private String productName;
    private String describe;
    private String price;
    private Integer realPrice;
    private Integer purchases;
    private List<Discount> discounts;

    public Product() {
    }

    public Product(Integer productID, String image, String productName, String describe, String price,
                   Integer realPrice, Integer purchases, List<Discount> discounts) {
        this.productID = productID;
        this.image = image;
        this.productName = productName;
        this.describe = describe;
        this.price = price;
        this.realPrice = realPrice;
        this.purchases = purchases;
        this.discounts = discounts;
    }

    protected Product(Parcel in) {
        if (in.readByte() == 0) {
            productID = null;
        } else {
            productID = in.readInt();
        }
        image = in.readString();
        productName = in.readString();
        describe = in.readString();
        price = in.readString();
        if (in.readByte() == 0) {
            realPrice = null;
        } else {
            realPrice = in.readInt();
        }
        if (in.readByte() == 0) {
            purchases = null;
        } else {
            purchases = in.readInt();
        }
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Integer realPrice) {
        this.realPrice = realPrice;
    }

    public Integer getPurchases() {
        return purchases;
    }

    public void setPurchases(Integer purchases) {
        this.purchases = purchases;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (productID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(productID);
        }
        dest.writeString(image);
        dest.writeString(productName);
        dest.writeString(describe);
        dest.writeString(price);
        if (realPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(realPrice);
        }
        if (purchases == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(purchases);
        }
    }
}
