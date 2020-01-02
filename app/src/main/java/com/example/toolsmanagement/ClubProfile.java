package com.example.toolsmanagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClubProfile implements Serializable {

    public String name;
    public List<Tool> tools_history= new ArrayList<>();

    public ClubProfile() {

    }

    public ClubProfile(String Name, List<Tool> tools_history) {
        this.name= Name;
        this.tools_history= tools_history;
    }

    public String getname() {
        return name;
    }

    public List<Tool> getTools_history() {return tools_history;}
}
