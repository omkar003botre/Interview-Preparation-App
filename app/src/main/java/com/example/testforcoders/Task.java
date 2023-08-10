package com.example.testforcoders;

import java.sql.Time;
import java.util.Date;

public class Task {
    private String name;
    private String description;
    private String date;
    private String time;
    int id;

    public Task( String name, String description , String date , String time) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getdate() {
        return date;
    }
    public String getTime(){
        return time;
    }


}