package com.source.code.springMybatis.service;


import com.source.code.springMybatis.dao.StaffDao;
import com.source.code.springMybatis.domain.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StaffImpl {

    @Autowired
    StaffDao staffDao;

    @Transactional
    public Staff selectById(){
        staffDao.selectById(1);
        System.out.println("====================");
        return staffDao.selectById(1);
    }
}
