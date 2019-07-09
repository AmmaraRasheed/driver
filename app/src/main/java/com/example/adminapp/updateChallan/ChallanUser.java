package com.example.adminapp.updateChallan;

public class ChallanUser {
    String buss_fee,bus_card_fee,transport_fund,total_of_fund,grand_total,date,duedate;

    public ChallanUser( String buss_fee, String bus_card_fee, String transport_fund, String total_of_fund, String grand_total, String date,String duedate) {

        this.buss_fee = buss_fee;
        this.bus_card_fee = bus_card_fee;
        this.transport_fund = transport_fund;
        this.total_of_fund = total_of_fund;
        this.grand_total = grand_total;
        this.duedate=duedate;
        this.date = date;
    }
    ChallanUser()
    {

    }
}
