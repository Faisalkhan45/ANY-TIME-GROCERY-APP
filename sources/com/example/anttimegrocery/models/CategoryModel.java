package com.example.anttimegrocery.models;

public class CategoryModel {
    String img_url;
    String name;
    String type;

    public CategoryModel() {
    }

    public CategoryModel(String img_url2, String name2, String type2) {
        this.img_url = img_url2;
        this.name = name2;
        this.type = type2;
    }

    public String getImg_url() {
        return this.img_url;
    }

    public void setImg_url(String img_url2) {
        this.img_url = img_url2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type2) {
        this.type = type2;
    }
}
