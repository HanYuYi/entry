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
                1,
                "山东豪克华光联合发展有限公司",
                "13033120917",
                "山东省济南市历城区将军路1111号",
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