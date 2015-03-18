package com.example.databaseapplication.app;

/**
 * Created by Marion on 3/18/2015.
 */
public class Contact {

    private String name;
    private long number;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", number=" + number
                ;
    }
}
