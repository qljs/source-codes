package com.source.code.mybatis.dao;

import com.source.code.mybatis.domain.Department;

public interface DepartmentDao {

    Department selectById(Integer id);

    int updateDepartment(Department department);
}
