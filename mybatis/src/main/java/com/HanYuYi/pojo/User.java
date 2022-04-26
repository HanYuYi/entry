package com.HanYuYi.pojo;

import java.sql.Date;

/**
 * 映射user_base表
 */
public class User {
    private long id;                            // 用户id
    private String avatar;                      // 用户头像
    private long userCode;                      // 用户编码
    private String userName;                    // 用户名
    private String userPassword;                // 密码
    private boolean gender;                     // 性别
    private String genderFmt;                   // 性别字符串
    private Date birthday;                      // 生日
    private String phone;                       // 电话
    private String address;                     // 地址
    private long userRole;                      // 角色id
    private long createBy;                      // 创建者id
    private Date createDate;                    // 创建时间
    private String createDateFmt;               // 创建时间字符串
    private long modifyBy;                      // 更新者id
    private Date modifyDate;                    // 更新时间
    private String modifyDateFmt;               // 更新时间字符串

    private int age;                            // 年龄
    private String userRoleName;                // 角色名称

    public User(long id, long userCode, String userName, String userPassword, boolean gender, Date birthday, String phone, String address, long userRole, long createBy, Date createDate, long modifyBy, Date modifyDate) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
        setGenderFmt(gender ? "男" : "女");
    }

    public void setGenderFmt(String gender) {
        this.genderFmt = gender;
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
        setCreateDateFmt(createDate.toString());
    }

    public void setCreateDateFmt(String createDateFmt) {
        this.createDateFmt = createDateFmt;
    }

    public void setModifyBy(long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
        setModifyDateFmt(modifyDate.toString());
    }

    public void setModifyDateFmt(String modifyDateFmt) {
        this.modifyDateFmt = modifyDateFmt;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }


    public long getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
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

    public boolean getGender() {
        return gender;
    }

    public String getGenderFmt() {
        return genderFmt;
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

    public String getCreateDateFmt() {
        return createDateFmt;
    }

    public long getModifyBy() {
        return modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public String getModifyDateFmt() {
        return modifyDateFmt;
    }

    public int getAge() {
        return age;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userCode=" + userCode +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", gender=" + gender +
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
