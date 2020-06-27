package com.adair.annotation.factory;

import com.adair.annotation.anno.DepartmentInfomation;
import com.adair.annotation.dao.SysUserDao;
import com.adair.annotation.model.SysDept;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class SysDeptUtils {

    //工厂模式下的创建dao
    public static SysUserDao createSysUserDao() {
        SysUserDao sysUserDao = new SysUserDao();
        try {
            //1.Introspector类获取beanInfo
            BeanInfo beanInfo = Introspector.getBeanInfo(sysUserDao.getClass(), Object.class);
            //2.beanInfo获取beanDescriptor，即sysUserDao里的所有属性、方法的集合
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null) {
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    /**
                     * java.beans.PropertyDescriptor
                     * 	[
                     * 		name=sysDept;
                     * 		propertyType=class com.adair.test.model.SysDept;
                     * 		readMethod=public com.adair.test.model.SysDept com.adair.test.dao.SysUserDao.getSysDept();
                     * 		writeMethod=public void com.adair.test.dao.SysUserDao.setSysDept(com.adair.test.model.SysDept)
                     * 	]
                     */
                    Method setMethod = propertyDescriptor.getWriteMethod();
                    //获取setSysDept方法名上的注解
                    DepartmentInfomation departmentInfomation = setMethod.getAnnotation(DepartmentInfomation.class);
                    if (departmentInfomation != null) {
                        //获取sysUserDao的SysDept类型
                        Class sysDeptClass = propertyDescriptor.getPropertyType();
                        //实例化SysDept对象
                        SysDept sysDept = (SysDept) sysDeptClass.newInstance();
                        //注解信息注入sysDept后的对象
                        sysDept = getSysDeptByDepartmentInfomation(departmentInfomation, sysDept);
                        //将sysDept信息注入到sysUserDao中setSysDept的参数sysDept中
                        setMethod.invoke(sysUserDao, sysDept);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysUserDao;
    }

    /**
     * 将注解注入到SysDept类
     */
    public static SysDept getSysDeptByDepartmentInfomation(DepartmentInfomation departmentInfomation, SysDept sysDept) {
        Method[] methods = DepartmentInfomation.class.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            try {
                // 注解类没有属性，反射注解类的方法名，内省出SysDept类设置参数的方法, 找不到会抛异常，进入下次循环
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, sysDept.getClass());
                // 注解类的方法能得到实际注解的值
                Object value = method.invoke(departmentInfomation, null);
                // 用SysDept的方法将注解类的值注入
                propertyDescriptor.getWriteMethod().invoke(sysDept, value);
            } catch (Exception e) {
                continue;
            }
        }
        return sysDept;
    }
}
