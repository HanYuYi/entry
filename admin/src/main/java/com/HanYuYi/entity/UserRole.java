package com.HanYuYi.entity;

import java.sql.Date;

/**
 * 映射user_role表
 */
public class UserRole {
    private long id;// 角色id
    private long roleCode;// 角色编码
    private String roleName;// 角色名称
    private long createBy;// 创建者id
    private Date createBydate; // 创建时间
    private long modifyBy; // 更新者id
    private Date modifyDate; // 更新时间

    public void setId(long id) {
        this.id = id;
    }

    public void setRoleCode(long roleCode) {
        this.roleCode = roleCode;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public void setCreateBydate(Date createBydate) {
        this.createBydate = createBydate;
    }

    public void setModifyBy(long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }


    public long getId() {
        return id;
    }

    public long getRoleCode() {
        return roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public long getCreateBy() {
        return createBy;
    }

    public Date getCreateBydate() {
        return createBydate;
    }

    public long getModifyBy() {
        return modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }
}
