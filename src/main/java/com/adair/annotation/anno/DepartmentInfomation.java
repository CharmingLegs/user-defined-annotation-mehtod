package com.adair.annotation.anno;

import java.lang.annotation.*;

/**
 * 自定义注解
 * @author Ibrahim.Ma
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DepartmentInfomation {

    String deptId() default "j32i-3rj-8i43-jti-tjgy";

    String deptName();

    String deptLoc();

    public enum type {MAX, MEDIA, MIN};

    type deptType() default type.MAX;
}
