package com.payrollsys.service;

import com.payrollsys.pojo.Employee;

import java.io.IOException;

public interface UserService {
    //user login
    Employee empLoginService(String empid, String password) throws IOException;

}
