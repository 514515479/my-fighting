package com.kon.fighting.service;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.common.persistence.BaseServiceImpl;
import com.kon.fighting.entity.BlogArticleTag;
import com.kon.fighting.mapper.BlogArticleTagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO               
 * @Author LK
 * @Date 2019/11/8 14:48
 * @Version V1.0
 **/
@Slf4j
@Service
public class BlogArticleTagService extends BaseServiceImpl<BlogArticleTag, Long> {

    @Autowired
    private BlogArticleTagMapper blogArticleTagMapper;

    @Override
    protected BaseMapper<BlogArticleTag> getMapper() {
        return this.blogArticleTagMapper;
    }

}
