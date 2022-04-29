package com.HanYuYi.pojo;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

/**
 * 偷懒工具，省去了些一些荣誉代码的时间，至于是否使用，还有待深究
 */
@Data
@ToString
public class UserByLombok {
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

    public UserByLombok(long id, long userCode, String userName, String userPassword, boolean gender, Date birthday, String phone, String address, long userRole, long createBy, Date createDate, long modifyBy, Date modifyDate) {
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

    public void setGender(boolean gender) {
        this.gender = gender;
        setGenderFmt(gender ? "男" : "女");
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
        setCreateDateFmt(createDate.toString());
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
        setModifyDateFmt(modifyDate.toString());
    }
}
