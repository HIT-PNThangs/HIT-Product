package com.example.hit.nhom5.product.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private int id;
    private int resourceId;
    private String title;
    private String picCate;

    public Category(int id, int resourceId, String title, String picCate) {
        this.id = id;
        this.resourceId = resourceId;
        this.title = title;
        this.picCate = picCate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPicCate() {
        return picCate;
    }

    public void setPicCate(String picCate) {
        this.picCate = picCate;
    }

    public static Creator<Category> getCREATOR() {
        return CREATOR;
    }

    protected Category(Parcel in) {
        id = in.readInt();
        resourceId = in.readInt();
        title = in.readString();
        picCate = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", title='" + title + '\'' +
                ", picCate='" + picCate + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(resourceId);
        dest.writeString(title);
        dest.writeString(picCate);
    }
}
