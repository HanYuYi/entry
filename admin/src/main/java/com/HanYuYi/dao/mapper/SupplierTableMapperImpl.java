package com.HanYuYi.dao.mapper;

import com.HanYuYi.entity.SupplierTable;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

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

    @Override
    public int update(Map map) {
        return getSqlSession().getMapper(SupplierTableMapper.class).update(map);
    }

    @Override
    public List<SupplierTable> selectById(int id) {
        return getSqlSession().getMapper(SupplierTableMapper.class).selectById(id);
    }

    @Override
    public List<SupplierTable> selectAll(Integer pageSize, Integer pageIndex) {
        return getSqlSession().getMapper(SupplierTableMapper.class).selectAll(pageSize, pageIndex);
    }

}
