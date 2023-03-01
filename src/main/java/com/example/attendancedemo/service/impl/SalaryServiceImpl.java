package com.example.attendancedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendancedemo.entity.Salary;
import com.example.attendancedemo.mapper.SalaryMapper;
import com.example.attendancedemo.service.SalaryService;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements SalaryService {
}
