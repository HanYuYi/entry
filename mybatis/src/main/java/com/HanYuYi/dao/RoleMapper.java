package com.HanYuYi.dao;

import com.HanYuYi.pojo.RoleByLombok;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    /**
     * 查询角色列表
     * @return
     */
    RoleByLombok getRoleList();
}
