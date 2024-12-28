package com.example.anttimegrocery.models;

public class AddressModel {
    boolean isSelected;
    String userAddress;

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String userAddress2) {
        this.userAddress = userAddress2;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }
}
