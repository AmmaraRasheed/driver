package com.example.adminapp.StudentRecord;

public class RecycleItem {
    private String name,father_name,phone,email,status,cnic,rollnumber,userclass,id;
    public RecycleItem() {
    }

    public RecycleItem(String name, String father_name, String phone, String email, String status, String cnic, String rollnumber, String userclass,String id) {
        this.name = name;
        this.id=id;
        this.father_name = father_name;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.cnic = cnic;
        this.rollnumber = rollnumber;
        this.userclass = userclass;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getCnic() {
        return cnic;
    }

    public String getRollnumber() {
        return rollnumber;
    }

    public String getUserclass() {
        return userclass;
    }
}
