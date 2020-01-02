package com.example.toolsmanagement;

public class ToolsListN {
    public String name;
    public Long uid;
    public Boolean availability;
    public String details;
    public String issued_to;
    public String return_date;

    public ToolsListN() {

    }

    public ToolsListN(String Name, Long uid, Boolean availability, String details, String issued_to, String return_date) {
        this.name= Name;
        this.uid= uid;
        this.availability= availability;
        this.details= details;
        this.issued_to= issued_to;
        this.return_date= return_date;
    }

    public Long getUid() {
        return uid;
    }

    public String getname() {
        return name;
    }

    public Boolean getAvailability() {return availability;}

    public String getDetails() { return details;}

    public String getIssued_to() { return issued_to;}

    public String getReturn_date() { return return_date;}
}
