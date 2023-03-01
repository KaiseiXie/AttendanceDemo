package com.example.attendancedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.attendancedemo.entity.Attendance;
import com.example.attendancedemo.mapper.AttendanceMapper;
import com.example.attendancedemo.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {

    /**
     * 根据传入的出勤时间和获取当前退勤时间来计算工作时长的方法
     * @param timesIn
     * @return
     */
    public String timeCalculator(Long timesIn) {

        Date date = new Date();
        Long timesOut = date.getTime();
        long diffInMills = timesOut - timesIn;
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMills);

        long h = toMinutes/60;
        long m = toMinutes%60;
        String workTime = h+":"+m;

        return workTime;
    }

    public String timeCalculator(LocalDateTime clockInTime,LocalDateTime clockOutTime) {

        long timesIn  = clockInTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        long timesOut  = clockOutTime.toInstant(ZoneOffset.UTC).toEpochMilli();

        long diffInMills = timesOut - timesIn;
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMills);

        long h = toMinutes/60;
        long m = toMinutes%60;
        String workTime = h+":"+m;

        return workTime;
    }


}
