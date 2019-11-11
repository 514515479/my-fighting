package com.kon.fighting.mapper;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.entity.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * @Description 菜单Mapper
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectAllPermission();

    List<SysMenu> selectMenuByMap(Map<String, Object> map);

}