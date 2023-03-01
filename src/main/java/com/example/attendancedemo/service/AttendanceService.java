package com.example.attendancedemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.attendancedemo.entity.Attendance;

import java.time.LocalDateTime;

public interface AttendanceService extends IService<Attendance> {
    String timeCalculator(Long timeIn);
    String timeCalculator(LocalDateTime clockInTime, LocalDateTime clockOutTime);
}
