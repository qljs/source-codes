package com.source.code.springMybatis.dao;

import com.source.code.springMybatis.domain.Department;

public interface DepartmentDao {

    Department selectById(Integer id);

    int updateDepartment(Department department);
}
