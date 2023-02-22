package com.example.attendancedemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.attendancedemo.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {
}
