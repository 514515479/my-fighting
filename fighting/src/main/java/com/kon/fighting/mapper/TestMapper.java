package com.kon.fighting.mapper;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.entity.Test;

/**
 * @Description 测试Mapper
 * @Author LK
 * @Date 2019/11/1 12:14
 * @Version V1.0
 **/
public interface TestMapper extends BaseMapper<Test> {

    Test custom(Long id);

}