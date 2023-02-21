package com.example.attendancedemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Long id;

    private String username;

    private LocalDateTime clockInTime;

    private LocalDateTime clockOutTime;

}
