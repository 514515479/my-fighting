package com.kon.fighting.service;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.common.persistence.BaseServiceImpl;
import com.kon.fighting.entity.Test;
import com.kon.fighting.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 测试接口
 * @Author LK
 * @Date 2019/10/31 17:57
 * @Version V1.0
 **/
@Service
public class TestService extends BaseServiceImpl<Test, Long> {

    @Autowired
    private TestMapper testMapper;

    @Override
    protected BaseMapper<Test> getMapper() {
        return testMapper;
    }

    /**
     * 自定义sql测试
     *
     * @param id
     * @return
     */
    public Test customSql(Long id) {
        return testMapper.custom(id);
    }

}








