package com.example.attendancedemo.core.domain;

import jdk.jfr.DataAmount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import lombok.Data;

@Data
public class Employee {

    private String name;

    private int id;

    private Date[] ondutytime;

    private Date[] offdutytime;

}