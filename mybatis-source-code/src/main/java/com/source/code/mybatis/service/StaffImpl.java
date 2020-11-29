package com.source.code.mybatis.service;

import com.source.code.mybatis.dao.StaffDao;
import com.source.code.mybatis.domain.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffImpl {

    @Autowired
    StaffDao staffDao;

    public Staff selectById(){
        return staffDao.selectById(1);
    }
}
