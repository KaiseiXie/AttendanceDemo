package com.example.attendancedemo.entity;

import lombok.Data;

@Data
public class User {

    //ユーザー名
    private String username;

    //ユーザーの本名
    private String name;

    //電話番号
    private Long phone;

    //暗証番号
    private String password;

    private Long id;

    //働いて工場
    private String factory;

    private int sex;

    //工作状态
    private Integer workStatus = 0;

}
