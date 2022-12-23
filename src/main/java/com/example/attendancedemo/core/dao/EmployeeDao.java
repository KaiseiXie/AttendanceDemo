package com.example.attendancedemo.core.dao;

import com.example.attendancedemo.core.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EmployeeDao {
    @Select("select * from t_time where id = #{id}")
    public Employee getById(Integer id);

}
