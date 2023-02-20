package com.example.attendancedemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendancedemo.common.R;
import com.example.attendancedemo.entity.Employee;
import com.example.attendancedemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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
        if (password == employeeService.getById(1).getPassword()){
            request.getSession().setAttribute("employee",employee);
            return R.success(employee);
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

}
