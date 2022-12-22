package com.example.attendancedemo.core.domain;

import java.util.Arrays;
import java.util.Date;

public class Employee {

    private String name;

    private int id;

    private Date[] ondutytime;

    private Date[] offdutytime;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", ondutytime=" + Arrays.toString(ondutytime) +
                ", offdutytime=" + Arrays.toString(offdutytime) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date[] getOndutytime() {
        return ondutytime;
    }

    public void setOndutytime(Date[] ondutytime) {
        this.ondutytime = ondutytime;
    }

    public Date[] getOffdutytime() {
        return offdutytime;
    }

    public void setOffdutytime(Date[] offdutytime) {
        this.offdutytime = offdutytime;
    }
}
