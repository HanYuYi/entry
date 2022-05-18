package com.HanYuYi.dao.mapper;

import com.HanYuYi.entity.SupplierTable;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 供应商
 */
public class SupplierTableMapperImpl extends SqlSessionDaoSupport implements SupplierTableMapper {

    /**
     * 新增供应商
     * @param Supplier
     * @return
     */
    @Override
    public int insert(SupplierTable Supplier) {
        return getSqlSession().getMapper(SupplierTableMapper.class).insert(Supplier);
    }

    @Override
    public int delete(int id) {
        return getSqlSession().getMapper(SupplierTableMapper.class).delete(id);
    }

}
