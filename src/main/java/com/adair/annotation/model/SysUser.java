package com.adair.annotation.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
/**
 * 实体类SysUser
 * @author Ibrahim.Ma
 */
@Data
@ToString
public class SysUser implements Serializable {

    private Integer id;
    private String username;
    private String password;

}
