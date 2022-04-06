package com.HanYuYi.entity;

import java.util.Date;

/**
 * 映射user_base表
 */
public class UserBase {
    private long id; // 用户id
    private long userCode; // 用户编码
    private String userName; // 用户名
    private String userPassword; // 密码
    private boolean gender; // 性别
    private Date birthday; // 生日
    private String phone; // 电话
    private String address; // 地址
    private long userRole; // 角色id
    private long createBy; // 创建者id
    private Date createDate; // 创建时间
    private long modifyBy; // 更新者id
    private Date modifyDate; // 更新时间

    private int age;// 年龄
    private int userRoleName;// 角色名称

    public void setId(long id) {
        this.id = id;
    }

    public void setUserCode(long userCode) {
        this.userCode = userCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserRole(long userRole) {
        this.userRole = userRole;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setModifyBy(long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUserRoleName(int userRoleName) {
        this.userRoleName = userRoleName;
    }


    public long getId() {
        return id;
    }

    public long getUserCode() {
        return userCode;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean isGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public long getUserRole() {
        return userRole;
    }

    public long getCreateBy() {
        return createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public long getModifyBy() {
        return modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public int getAge() {
        return age;
    }

    public int getUserRoleName() {
        return userRoleName;
    }

    @Override
    public String toString() {
        return "UserBase{" +
                "id=" + id +
                ", userCode=" + userCode +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userRole=" + userRole +
                ", createBy=" + createBy +
                ", createDate=" + createDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", age=" + age +
                ", userRoleName=" + userRoleName +
                '}';
    }
}
