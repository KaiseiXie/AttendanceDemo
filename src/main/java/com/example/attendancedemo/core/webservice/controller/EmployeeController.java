package com.example.attendancedemo.core.webservice.controller;

import com.example.attendancedemo.core.domain.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @PostMapping
    public String save(@RequestBody Employee employee){
        System.out.println("employee"+employee);
        return "'module':'employee save success'";
    }

    @GetMapping
    public List<Employee> getAll(){
        List<Employee> employeeslist = new ArrayList<Employee>();

        Employee employee1 = new Employee();
        employee1.setName("DonaldTrump");
        employee1.setId(11111);
        employee1.setOndutytime(new Date[]{});
        employeeslist.add(employee1);

        Employee employee2 = new Employee();
        employee2.setName("JohnBiden");
        employee2.setId(22222);
        employee2.setOndutytime(new Date[]{});
        employeeslist.add(employee2);

        return employeeslist;
    }
}
