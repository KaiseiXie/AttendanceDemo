package com.example.attendancedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendancedemo.entity.Attendance;
import com.example.attendancedemo.mapper.AttendanceMapper;
import com.example.attendancedemo.service.AttendanceService;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {
}
