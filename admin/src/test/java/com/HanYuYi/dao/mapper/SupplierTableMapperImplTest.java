package com.HanYuYi.dao.mapper;

import com.HanYuYi.entity.SupplierTable;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.UUID;

class SupplierTableMapperImplTest {

    @Test
    void insert() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SupplierTableMapper supplierTableMapper = context.getBean("supplierTableMapper", SupplierTableMapper.class);
        SupplierTable supplierTable = new SupplierTable(
                2,
                "北京纳福尔食用油有限公司",
                "17122110913",
                "北京市丰台区惠翔国际信鸽产业园",
                new Date()
        );
        int insertIndex = supplierTableMapper.insert(supplierTable);
        System.out.println(insertIndex);
    }

    @Test
    void delete() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SupplierTableMapper supplierTableMapper = context.getBean("supplierTableMapper", SupplierTableMapper.class);
        int deleteIndex = supplierTableMapper.delete(1);
        System.out.println(deleteIndex);
    }
}