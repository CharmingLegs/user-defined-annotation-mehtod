package com.adair.annotation.model;

import lombok.Data;
import lombok.ToString;

/**
 * 实体类SysDept
 * @author Ibrahim.Ma
 */
@Data
@ToString
public class SysDept {

    private String deptId;

    private String deptName;

    private String deptLoc;

    private String deptType;
}
