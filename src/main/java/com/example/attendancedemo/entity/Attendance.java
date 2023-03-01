package com.example.attendancedemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    //操作者id
    private Long id;

    private String username;

    //出勤时刻的绝对时间，也可用作出勤编号
    private Long timesIn;

    private LocalDateTime clockInTime;

    private LocalDateTime clockOutTime;

    //出勤时间
    private String workTime;

}
