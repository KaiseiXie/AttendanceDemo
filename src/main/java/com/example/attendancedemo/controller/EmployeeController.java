package com.example.attendancedemo.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendancedemo.common.R;
import com.example.attendancedemo.entity.Attendance;
import com.example.attendancedemo.entity.Employee;
import com.example.attendancedemo.service.AttendanceService;
import com.example.attendancedemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttendanceService attendanceService;

    /**
     * 实现员工登录功能
     * @param request
     * @param employee
     * @return
     * @throws IOException
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request , @RequestBody Employee employee)  {
        //1.将页面提交密码进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据页面提交的用户名查询username数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //3.如果没有查询到则返回登录失败结果
        if (emp == null){
            return R.error("登陆失败");
        }
        //4.如果查询到则进行比对，比对成功则把数据存入session，比对失败则返回失败结果
        if (password.equals(emp.getPassword())){
            request.getSession().setAttribute("employee",emp.getId());
            return R.success(emp);
        } return R.error("密码错误，登录失败");
    }

    /**
     * 社員追加メソッド
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Employee employee){
        log.info("社員{}を追加",employee.toString());
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        employeeService.save(employee);
        return R.success("追加しました");
    }

    /**
     * 查询员工分页信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){

        log.info("page = {},pageSize = {},name = {}",page,pageSize,name);
        //构建分页构造器
        Page pageInfo = new Page(page,pageSize);
        //创造条件构造器
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        lambdaQueryWrapper.like(name != null,Employee::getName,name);
        //添加排序条件
        lambdaQueryWrapper.orderByDesc(Employee::getId);

        //执行查询
        employeeService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(Employee employee){
        return null;
    }

    @DeleteMapping("/delete")
    public R<String> delete(){
        return  null;
    }

    /**
     * 实现员工上班打卡功能
     * @param request
     * @return
     */
    @PostMapping("/clockin")
    public R<Attendance> clockIn(HttpServletRequest request){

        //获取打卡用户id并输出到控制台
        String employeeId = request.getSession().getAttribute("employee").toString();
        log.info("当前打卡用户id为={}",employeeId);

        //通过打卡用户id从员工表中获取打卡员工对象
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(!employeeId.isEmpty(),Employee::getId,employeeId);
        Employee emp = employeeService.getOne(queryWrapper);


        //通过查询数据库判断当前员工工作状态
            //如果不在工作状态，则新建一个打卡对象传入数据库，把本次记录标识传入session，并返回打卡成功结果
        if (emp.getWorkStatus() == 0) {

            LambdaUpdateWrapper<Employee> updateWrapper = new LambdaUpdateWrapper();
            updateWrapper.eq(Employee::getId,emp.getId());
            updateWrapper.set(Employee::getWorkStatus,1);
            employeeService.update(updateWrapper);

            Attendance atd = new Attendance();
            atd.setId(emp.getId());
            atd.setName(emp.getName());
            atd.setUsername(emp.getUsername());
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
     * 实现退勤功能
     * @param request
     * @return
     */
    @PostMapping("/clockout")
    public R<String> clockOut(HttpServletRequest request){
        //获取退勤打卡用户id并输出到控制台
        String employeeId = request.getSession().getAttribute("employee").toString();
        log.info("当前退勤打卡用户id为={}",employeeId);

        //根据id查询当前员工工作状态
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(!employeeId.isEmpty(),Employee::getId,employeeId);
        Employee emp = employeeService.getOne(queryWrapper);

        //如果处于工作状态则记录下班时刻，并将工作状态改为非工作状态
        if (emp.getWorkStatus() == 1){
            LambdaUpdateWrapper<Employee> updateWrapper = new LambdaUpdateWrapper();
            updateWrapper.eq(Employee::getId,emp.getId());
            updateWrapper.set(Employee::getWorkStatus,0);
            employeeService.update(updateWrapper);

        //通过times找到attendance表中对应记录，计入退勤时间，计算出工作市场，并更新记录,且删除session中的该次考勤记录标识
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
