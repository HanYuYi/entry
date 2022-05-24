package com.HanYuYi.dao.mapper;

import com.HanYuYi.entity.SupplierTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

class SupplierTableMapperImplTest {
    SupplierTableMapper supplierTableMapper = null;

    @BeforeEach
    void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        supplierTableMapper = context.getBean("supplierTableMapper", SupplierTableMapper.class);
    }

    @Test
    void insert() {
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
        int deleteIndex = supplierTableMapper.delete(1);
        System.out.println(deleteIndex);
    }

    @Test
    void update() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", 2);
        params.put("name", "北京纳福尔调味品有限公司");
        params.put("phone", "13988741247");

        int updateIndex = supplierTableMapper.update(params);
        System.out.println(updateIndex);
    }

    @Test
    void selectById() {
        List<SupplierTable> supplierTables = supplierTableMapper.selectById(1);
        System.out.println(supplierTables);
    }

    @Test
    void selectAll() {

    }
}