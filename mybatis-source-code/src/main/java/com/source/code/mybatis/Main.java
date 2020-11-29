package com.source.code.mybatis;

import com.source.code.mybatis.config.MybatisConfig;
import com.source.code.mybatis.dao.DepartmentDao;
import com.source.code.mybatis.dao.StaffDao;
import com.source.code.mybatis.domain.Department;
import com.source.code.mybatis.domain.Staff;
import com.source.code.mybatis.service.StaffImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MybatisConfig.class);
        SqlSessionFactory sessionFactory = context.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = sessionFactory.openSession();
        StaffDao mapper = sqlSession.getMapper(StaffDao.class);
        Staff staff = new Staff();
        staff.setId(1);
        staff.setName("name");
        mapper.updateStaff(staff);


    }
}
