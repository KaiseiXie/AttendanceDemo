package com.example.attendancedemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Long id;

    private String username;

    //出勤日期
    private Long times;

    private LocalDateTime clockInTime;

    private LocalDateTime clockOutTime;

}
