package com.source.code.springMybatis.dao;

import com.source.code.springMybatis.domain.Staff;

public interface StaffDao {

    Staff selectById(Integer id);

    int updateStaff(Staff staff);
}
