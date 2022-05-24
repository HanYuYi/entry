package com.HanYuYi.dao.mapper;

import com.HanYuYi.entity.SupplierTable;

import java.util.List;
import java.util.Map;

public interface SupplierTableMapper {

    int insert(SupplierTable Supplier);

    int delete(int id);

    int update(Map map);

    List<SupplierTable> selectById(int id);

    List<SupplierTable> selectAll(Integer pageSize, Integer pageIndex);
}
