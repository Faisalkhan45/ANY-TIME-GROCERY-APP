package com.example.anttimegrocery.models;

import java.io.Serializable;

public class NewProductsModel implements Serializable {
    String description;
    String img_url;
    String name;
    int price;
    String rating;

    public NewProductsModel() {
    }

    public NewProductsModel(String description2, String name2, String rating2, int price2, String img_url2) {
        this.description = description2;
        this.name = name2;
        this.rating = rating2;
        this.price = price2;
        this.img_url = img_url2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description2) {
        this.description = description2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating2) {
        this.rating = rating2;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price2) {
        this.price = price2;
    }

    public String getImg_url() {
        return this.img_url;
    }

    public void setImg_url(String img_url2) {
        this.img_url = img_url2;
    }
}
