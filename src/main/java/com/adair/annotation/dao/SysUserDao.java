package com.adair.annotation.dao;

import com.adair.annotation.anno.DepartmentInfomation;
import com.adair.annotation.model.SysDept;

/**
 * @author Ibrahim.Ma
 */
public class SysUserDao {

    private SysDept sysDept;

    public SysDept getSysDept() {
        return sysDept;
    }

    @DepartmentInfomation(deptId = "0001", deptName = "研发部", deptLoc = "银川市兴庆区长城东路211号", deptType = DepartmentInfomation.type.MAX)
    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }
}
