package com.example.attendancedemo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    //社員名前
    private String name;

    //用户名称
    private String username;

    //社員id
    private Long id;

    //性別
    private String sex;

    private String phone;

    private String password;
}