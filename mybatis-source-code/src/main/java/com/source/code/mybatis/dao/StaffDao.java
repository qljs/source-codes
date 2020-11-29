package com.source.code.mybatis.dao;

import com.source.code.mybatis.domain.Staff;
import com.source.code.mybatis.domain.StaffDO;

public interface StaffDao {

    Staff selectById(Integer id);

    StaffDO selectById2(Integer id);

    int updateStaff(Staff staff);
}
