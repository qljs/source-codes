package com.source.code.mybatis.domain;

import com.source.code.mybatis.domain.Staff;

public class StaffDO extends Staff {

    private String depName;

    public StaffDO() {
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public String toString() {
        return "StaffDO{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", depId=" + super.getDepId() + '\'' +
                ", depName='" + depName + '\'' +
                '}';
    }
}
