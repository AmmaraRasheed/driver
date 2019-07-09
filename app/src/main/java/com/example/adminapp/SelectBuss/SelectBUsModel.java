package com.example.adminapp.SelectBuss;

public class SelectBUsModel {

    String bussNumber,busId;

    public SelectBUsModel(String bussNumber, String busId) {
        this.bussNumber = bussNumber;
        this.busId=busId;
    }

    public String getBussNumber() {
        return bussNumber;
    }

    public String getBusId() {
        return busId;
    }
}
