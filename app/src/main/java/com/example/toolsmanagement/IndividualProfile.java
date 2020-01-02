package com.example.toolsmanagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IndividualProfile implements Serializable {

    public String name;
    public Long roll_No;
    public List<Tool> tools_history= new ArrayList<>();

    public IndividualProfile() {

    }

    public IndividualProfile(String Name, Long Roll_No, List<Tool> tools_history) {
        this.name= Name;
        this.roll_No= Roll_No;
        this.tools_history= tools_history;
    }

    public Long getroll_No() {
        return roll_No;
    }

    public String getname() {
        return name;
    }

    public List<Tool> getTools_history() {return tools_history;}

}
