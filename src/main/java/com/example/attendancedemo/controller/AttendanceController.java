package com.example.attendancedemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.attendancedemo.common.R;
import com.example.attendancedemo.entity.Attendance;
import com.example.attendancedemo.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/list")
    public R<List<Attendance>> list(HttpServletRequest request){

        String attendanceId = request.getSession().getAttribute("attendance").toString();
        LambdaQueryWrapper<Attendance> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(!attendanceId.isEmpty(),Attendance::getTimes,attendanceId);

        return R.success(attendanceService.list(queryWrapper));
    }

    /**
     * 全員勤怠状況一覧
     * @return
     */
    @GetMapping("/listall")
    public R<List<Attendance>> listAll(HttpServletRequest request){





        return null;
    }
}
