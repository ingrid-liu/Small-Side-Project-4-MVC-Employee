package com.payrollsys.service.impl;

import com.payrollsys.mapper.UserMapper;
import com.payrollsys.pojo.Employee;
import com.payrollsys.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


//todo Learn more about myBatis: https://mybatis.org/mybatis-3/java-api.html

public class UserServiceImpl implements UserService {
    // User login

    @Override
    public Employee empLoginService(String empid, String password) throws IOException {
        //Get the SqlSession object
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        //Get instantiated object of Mapper interface
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.empLoginMapper(empid, password);
    }
}
