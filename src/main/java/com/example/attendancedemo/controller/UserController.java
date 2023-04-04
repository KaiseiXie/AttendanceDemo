package com.example.attendancedemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.attendancedemo.common.R;
import com.example.attendancedemo.entity.Attendance;
import com.example.attendancedemo.entity.Employee;
import com.example.attendancedemo.entity.User;
import com.example.attendancedemo.service.AttendanceService;
import com.example.attendancedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;


    /**
     * 劳动者登录功能
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user){

        //1.将页面提交密码进行md5加密处理
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据页面提交的电弧号码查询username数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone,user.getPhone());
        User user1 = userService.getOne(queryWrapper);

        //3.如果没有查询到则返回登录失败结果
        if (user1 == null){
            return R.error("登陆失败");
        }
        //4.如果查询到则进行比对，比对成功则把数据存入session，比对失败则返回失败结果
        if (password.equals(user1.getPassword())){
            request.getSession().setAttribute("user",user1.getId());
            return R.success(user1);
        } return R.error("密码错误，登录失败");

    }


    @PostMapping("/clockin")
    public R<Attendance> clockIn(HttpServletRequest request){

        //获取打卡用户id并输出到控制台
        String userId = request.getSession().getAttribute("user").toString();
        log.info("当前打卡用户id为={}",userId);

        //通过打卡用户id从员工表中获取打卡员工对象
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(!userId.isEmpty(),User::getId,userId);
        User usr = userService.getOne(queryWrapper);


        //通过查询数据库判断当前员工工作状态
        //如果不在工作状态，则新建一个打卡对象传入数据库，把本次记录标识传入session，并返回打卡成功结果
        if (usr.getWorkStatus() == 0) {

            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper();
            updateWrapper.eq(User::getId,usr.getId());
            updateWrapper.set(User::getWorkStatus,1);
            userService.update(updateWrapper);

            Attendance atd = new Attendance();
            atd.setId(usr.getId());
            atd.setName(usr.getName());
            atd.setUsername(usr.getUsername());
            atd.setClockInTime(LocalDateTime.now());
            Date date =new Date();
            atd.setTimesIn(date.getTime());

            attendanceService.save(atd);

            request.getSession().setAttribute("attendance",atd.getTimesIn());

            return R.success(atd);
        }else{
            //如果在工作状态，则提示错误信息
            return R.error("打卡失败");
        }

    }

    /**
     * 退勤功能
     * @param request
     * @return
     */
    @PostMapping("/clockout")
    public R<String> clockOut(HttpServletRequest request){
        //获取退勤打卡用户id并输出到控制台
        String userId = request.getSession().getAttribute("user").toString();
        log.info("当前退勤打卡用户id为={}",userId);

        //根据id查询当前员工工作状态
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(!userId.isEmpty(),User::getId,userId);
        User usr = userService.getOne(queryWrapper);

        //如果处于工作状态则记录下班时刻，并将工作状态改为非工作状态
        if (usr.getWorkStatus() == 1){
            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper();
            updateWrapper.eq(User::getId,usr.getId());
            updateWrapper.set(User::getWorkStatus,0);
            userService.update(updateWrapper);

            //通过times找到attendance表中对应记录，计入退勤时间，计算出工作时长，并更新记录,且删除session中的该次考勤记录标识
            LambdaUpdateWrapper<Attendance> updateWrapper1 = new LambdaUpdateWrapper();
            updateWrapper1.eq(Attendance::getTimesIn,request.getSession().getAttribute("attendance"));
            updateWrapper1.set(Attendance::getClockOutTime,LocalDateTime.now());

            //计算时间间隔
            Long timesIn = attendanceService.getOne(updateWrapper1).getTimesIn();
            String workTime = attendanceService.timeCalculator(timesIn);
            updateWrapper1.set(Attendance::getWorkTime,workTime);

            //更新记录，删除考勤标识
            attendanceService.update(updateWrapper1);
            request.getSession().removeAttribute("attendance");
        }

        //如果处于非工作状态，则返回错误信息

        return R.success("退勤成功");
    }

}
