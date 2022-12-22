package com.example.attendancedemo.core.webservice.controller;

import com.example.attendancedemo.core.domain.MarkTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;


@RestController
@RequestMapping("/employeetime")

public class EmployeeTimeController {

    @RequestMapping("/marktime")
    public void markTime(MarkTime marktime,String time) {
        System.out.println(marktime.getWorkin());
        System.out.println(time);
    }

    @RequestMapping("/date")
    public void Date(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date){
        System.out.println(date);
    }

}
