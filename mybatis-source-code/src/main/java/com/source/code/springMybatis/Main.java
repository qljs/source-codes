package com.source.code.springMybatis;

import com.source.code.springMybatis.service.StaffImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
        StaffImpl staff = context.getBean(StaffImpl.class);
        staff.selectById();
    }
}
