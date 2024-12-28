package com.example.anttimegrocery.models;

public class MyCartModel {
    String currentDate;
    String currentTime;
    String productName;
    String productPrice;
    int totalPrice;
    String totalQuantity;

    public MyCartModel() {
    }

    public MyCartModel(String currentTime2, String currentDate2, String productName2, String productPrice2, String totalQuantity2, int totalPrice2) {
        this.currentTime = currentTime2;
        this.currentDate = currentDate2;
        this.productName = productName2;
        this.productPrice = productPrice2;
        this.totalQuantity = totalQuantity2;
        this.totalPrice = totalPrice2;
    }

    public String getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(String currentTime2) {
        this.currentTime = currentTime2;
    }

    public String getCurrentDate() {
        return this.currentDate;
    }

    public void setCurrentDate(String currentDate2) {
        this.currentDate = currentDate2;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String currentName) {
        this.productName = currentName;
    }

    public String getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(String currentPrice) {
        this.productPrice = currentPrice;
    }

    public String getTotalQuantity() {
        return this.totalQuantity;
    }

    public void setTotalQuantity(String currentQuantity) {
        this.totalQuantity = currentQuantity;
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(int totalPrice2) {
        this.totalPrice = totalPrice2;
    }
}
