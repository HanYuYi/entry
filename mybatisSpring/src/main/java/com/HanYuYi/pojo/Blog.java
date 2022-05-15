package com.HanYuYi.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Blog implements Serializable {
    private String id;
    private String title;
    private String author;
    private Date createTime;
    private int views;
}
