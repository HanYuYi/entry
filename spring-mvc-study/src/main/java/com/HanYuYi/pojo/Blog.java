package com.HanYuYi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog implements Serializable {
    private String id;
    private String title;
    private String author;
    private Date createTime;
    private int views;
}
