package com.HanYuYi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierTable {
    private int id;
    private String name;
    private String phone;
    private String address;
    private Date createTime;
}
