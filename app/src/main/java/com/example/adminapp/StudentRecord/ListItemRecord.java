package com.example.adminapp.StudentRecord;

public class ListItemRecord {
    String name,rollNumber,phone,email,fatherName,classuser,unique_id;

    public ListItemRecord(String name, String rollNumber, String phone, String email, String fatherName, String classuser,String unique_id) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.phone = phone;
        this.email = email;
        this.fatherName = fatherName;
        this.classuser = classuser;
        this.unique_id=unique_id;

    }

    public String getUnique_id() {
        return unique_id;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }
    public String getPhone() {
        return phone;
    }


    public String getEmail() {
        return email;
    }

    public String getFatherName() {
        return fatherName;
    }
    public String getClassuser() {
        return classuser;
    }
}
