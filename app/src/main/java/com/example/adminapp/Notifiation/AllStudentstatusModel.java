package com.example.adminapp.Notifiation;

public class AllStudentstatusModel {
    String name,status,image,id;
    private boolean isSelect=false;

    public AllStudentstatusModel(String name, String status, String image,String id) {
        this.name = name;
        this.status = status;
        this.image = image;
        this.id=id;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }
    public boolean isSelect() {

        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
