package com.HanYuYi.pojo;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
public class RoleByLombok {
    private long id;                                        // 角色id
    private long roleCode;                                  // 角色code
    private String roleName;                                // 角色名称
    private long createBy;                                  // 角色创建人
    private Date createDate;                                // 角色创建时间
    private long modifyBy;                                  // 角色最后更新人
    private Date modifyDate;                                // 角色最后更新时间
}
