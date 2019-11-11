package com.kon.fighting.mapper;

import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.entity.BlogTag;
import com.kon.fighting.entity.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description TODO               
 * @Author LK
 * @Date 2019/11/8 14:48
 * @Version V1.0
 **/
public interface BlogTagMapper extends BaseMapper<BlogTag> {

    /**
     * 根据博客文章id查询标签List
     *
     * @param articleId
     * @return
     */
    @Select("select bt.* from blog_tag bt,blog_article_tag bat \n" +
            "where bt.id = bat.tag_id \n" +
            "and bt.del_flag = 0 \n" +
            "and bat.article_id = #{articleId} \n" +
            "order by bt.sort")
    List<BlogTag> selectBlogTagListByArticleId(Long articleId);

}