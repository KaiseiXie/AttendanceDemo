package com.example.attendancedemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.attendancedemo.entity.WorkTime;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkTimeMapper extends BaseMapper<WorkTime> {
}
