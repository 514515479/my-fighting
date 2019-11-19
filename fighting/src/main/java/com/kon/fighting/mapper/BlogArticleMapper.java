package com.kon.fighting.mapper;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.entity.BlogArticle;

import java.util.List;
import java.util.Map;

/**
 * @Description 博客文章Mapper
 * @Author LK
 * @Date 2019/11/8 14:48
 * @Version V1.0
 **/
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    List<BlogArticle> selectBlogArticlePageByMap(Map<String, Object> map);

    BlogArticle selectBlogArticleById(Long id);

    List<BlogArticle> selectHotBlogArticle(int num);

    List<Map<String, Object>> selectBlogArticleFilingByTime(int num);

}