package com.HanYuYi.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class OrderTable {
    private String id;
    private String oddNumbers;
    private String name;
    private int supplier;
    private BigDecimal amount;
    private boolean status;
    private Date createTime;
}
