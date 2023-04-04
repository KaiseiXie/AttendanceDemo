package com.example.attendancedemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.attendancedemo.common.BaseContext;
import com.example.attendancedemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
