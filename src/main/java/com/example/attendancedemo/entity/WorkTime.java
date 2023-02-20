package com.example.attendancedemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 記録対象
 */
@Data
public class WorkTime implements Serializable {

    private static final long serialVersionUID = 1L;

    //記録id
    private Long id;

    //社員id
    private Long epeId;

    //記録時間
    private LocalDateTime createTime;

    //出勤時間
    private LocalDateTime onTime;

    //退勤時間
    private LocalDateTime outTime;

}
