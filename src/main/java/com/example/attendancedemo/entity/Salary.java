package com.example.attendancedemo.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Salary {

    private static final long serialVersionUID = 1L;

    //出勤番号
    private Long worksIn;

    //社員名前
    private String employeeName;

    //計算者名前
    private String computeName;

    //日払いとか周払いとか
    private Integer workForm;

    //勤務時間
    private String workTime;

    //給料
    private Integer pay;

    //勤務日期
    private LocalDate workDate;

    //計算日期
    private LocalDate computeDate;

    //付記
    private String detail;
}
