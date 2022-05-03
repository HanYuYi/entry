package com.HanYuYi.pojo;

import lombok.Data;

import java.util.List;

@Data
public class RoleByOneToMany {
    private long id;
    private String roleName;
    private List<RoleByOneToMany> userAll;
}
