package com.example.toolsmanagement;

public class ToolsListY {

    public String name;
    public Long uid;
    public Boolean availability;
    public String details;

    public ToolsListY() {

    }

    public ToolsListY(String Name, Long uid, Boolean availability, String details) {
        this.name= Name;
        this.uid= uid;
        this.availability= availability;
        this.details= details;
    }

    public Long getUid() {
        return uid;
    }

    public String getname() {
        return name;
    }

    public Boolean getAvailability() {return availability;}

    public String getDetails() { return details;}
}
