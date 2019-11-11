package com.kon.fighting.mapper;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.entity.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description 角色Mapper
 * @Author LK
 * @Date 2019/11/2 17:25
 * @Version V1.0
 **/
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id查询角色List
     *
     * @param userId
     * @return
     */
    @Select("select sr.* from sys_role sr, sys_user_role ur where sr.id = ur.role_id and sr.del_flag = 0 and ur.user_id = #{userId}")
    List<SysRole> selectRoleListByUserId(Long userId);

}