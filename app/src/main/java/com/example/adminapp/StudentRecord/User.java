package com.example.adminapp.StudentRecord;

public class User {
    String name,fatherName,pass,rollNumber,classuser,email,phone,unique_id,CNIC,status,status_result;

    User(String name, String fatherName, String rollNumber, String classuser, String email, String phone,String unique_id,String CNIC,String status,String status_result) {
        this.name = name;
        this.status_result=status_result;
        this.status=status;
        this.fatherName = fatherName;
        this.rollNumber = rollNumber;
        this.classuser = classuser;
        this.email = email;
        this.phone = phone;
        this.unique_id=unique_id;
        this.CNIC=CNIC;
    }
    User()
    {}
}
