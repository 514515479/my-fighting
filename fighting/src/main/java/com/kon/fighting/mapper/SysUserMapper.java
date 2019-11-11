package com.kon.fighting.mapper;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @Description 用户Mapper
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SuperUser> selectUserPageByMap(Map<String, Object> map);

}