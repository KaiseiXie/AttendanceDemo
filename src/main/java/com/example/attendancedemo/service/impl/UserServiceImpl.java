package com.example.attendancedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendancedemo.entity.User;
import com.example.attendancedemo.mapper.UserMapper;
import com.example.attendancedemo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
