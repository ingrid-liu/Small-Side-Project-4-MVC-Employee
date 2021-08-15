package com.payrollsys.mapper;

import com.payrollsys.pojo.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    //user login
    @Select("select * from employee where empid=#{empid} and password=#{password")
    // Employee empLoginMapper(String empid, String password);
    // ↓ a new way to add comments ↓
    Employee empLoginMapper(@Param("empid") String empid, @Param("password") String password);

}
