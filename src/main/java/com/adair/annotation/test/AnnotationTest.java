package com.adair.annotation.test;

import com.adair.annotation.dao.SysUserDao;
import com.adair.annotation.factory.SysDeptUtils;
import com.adair.annotation.model.SysDept;
/**
 * 测试类AnnotationTest
 * @author Ibrahim.Ma
 */
public class AnnotationTest {

    public static void main(String args[]) {

        SysUserDao createSysUserDao = SysDeptUtils.createSysUserDao();
        SysDept sysDept = createSysUserDao.getSysDept();
        String deptName = sysDept.getDeptName();
        System.out.println(deptName);
    }
}
