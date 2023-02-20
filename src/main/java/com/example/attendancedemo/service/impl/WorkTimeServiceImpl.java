package com.example.attendancedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendancedemo.entity.WorkTime;
import com.example.attendancedemo.mapper.WorkTimeMapper;
import com.example.attendancedemo.service.WorkTimeService;
import org.springframework.stereotype.Service;

@Service
public class WorkTimeServiceImpl extends ServiceImpl<WorkTimeMapper,WorkTime> implements WorkTimeService {
}
