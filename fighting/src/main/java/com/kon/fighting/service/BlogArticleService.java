package com.kon.fighting.service;

import com.kon.fighting.common.constant.CustomStaticValue;
import com.kon.fighting.common.dto.Page;
import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.common.persistence.BaseServiceImpl;
import com.kon.fighting.entity.*;
import com.kon.fighting.mapper.BlogArticleMapper;
import com.kon.fighting.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description TODO               
 * @Author LK
 * @Date 2019/11/8 14:48
 * @Version V1.0
 **/
@Slf4j
@Service
public class BlogArticleService extends BaseServiceImpl<BlogArticle, Long> {

    @Autowired
    private BlogArticleMapper blogArticleMapper;

    @Autowired
    private BlogArticleTagService blogArticleTagService;

    @Override
    protected BaseMapper<BlogArticle> getMapper() {
        return this.blogArticleMapper;
    }

    /**
     * 查询文章(开放接口)
     *
     * @param id 文章id
     * @return
     */
    public BlogArticle findBlogArticleById(Long id) {
        return blogArticleMapper.selectBlogArticleById(id);
    }

    /**
     * 查询文章分页list(开放接口)
     *
     * @param map 条件map
     * @return
     */
    public Page<BlogArticle> findBlogArticlePageByMapOpen(Map<String, Object> map) {
        // 封装tags参数，用于like查询
        String tags = (String) map.get("tags");
        if (!StringUtils.isEmpty(tags)) {
            map.put("tags", "#" + tags + "#");
        }
        // 只筛选发布的文章
        map.put("draftFlag", "0");
        List<BlogArticle> blogArticleList = blogArticleMapper.selectBlogArticlePageByMap(map);
        return Page.pageData(blogArticleList);
    }

    /**
     * 查询文章分页list
     *
     * @param map 条件map
     * @return
     */
    public Page<BlogArticle> findBlogArticlePageByMap(Map<String, Object> map) {
        // 判断是否个人模式
        if (CustomStaticValue.INDIVIDUAL_MODEL) {
            map.put("userId", SecurityUtils.getUser().getId());
        }
        // 封装tags参数，用于like查询
        String tags = (String) map.get("tags");
        if (!StringUtils.isEmpty(tags)) {
            map.put("tags", "#" + tags + "#");
        }
        List<BlogArticle> blogArticleList = blogArticleMapper.selectBlogArticlePageByMap(map);
        return Page.pageData(blogArticleList);
    }

    /**
     * 新增文章及文章标签关系
     *
     * @param entity 文章实体
     * @return
     */
    @Transactional
    public boolean saveBlogArticleAndBlogArticleTag(BlogArticle entity) {
        // 新增文章
        int articleInsertNum = this.saveSelective(entity);
        if (articleInsertNum > 0) {
            // 新增文章标签关系
            int articleTagInsertNum = this.saveArticleTag(entity.getId(), entity.getTagIdList());
            return articleTagInsertNum > 0;
        }
        return false;
    }

    /**
     * 修改文章及文章标签关系
     *
     * @param entity 文章实体
     * @return
     */
    @Transactional
    public boolean updateBlogArticleAndBlogArticleTag(BlogArticle entity) {
        // 修改文章
        int userUpdateNum = this.updateSelective(entity);
        if (userUpdateNum > 0) {
            // 删除原有的文章标签关系
            blogArticleTagService.delete(new BlogArticleTag().setArticleId(entity.getId()));
            // 新增文章标签关系
            int articleTagInsertNum = this.saveArticleTag(entity.getId(), entity.getTagIdList());
            return articleTagInsertNum > 0;
        }
        return false;
    }

    /**
     * 新增文章标签关系
     *
     * @param articleId 文章Id
     * @param tagIdList 标签Id集合
     * @return
     */
    private int saveArticleTag(Long articleId, Set<Long> tagIdList) {
        List<BlogArticleTag> blogArticleTagList = new ArrayList<>();
        BlogArticleTag blogArticleTag;
        for (Long tagId : tagIdList) {
            blogArticleTag = new BlogArticleTag().setArticleId(articleId).setTagId(tagId);
            blogArticleTagList.add(blogArticleTag);
        }
        return blogArticleTagService.saveList(blogArticleTagList);
    }

}
