package com.kon.fighting.entity;

import com.kon.fighting.common.persistence.BaseEntity;
import com.kon.fighting.utils.CollectionUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Set;

/**
 * @Description TODO
 * @Author LK
 * @Date 2019/11/8 14:48
 * @Version V1.0
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(name = "blog_article")
public class BlogArticle extends BaseEntity<Long> implements Serializable {

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 内容
     */
    @Column(name = "body")
    private String body;

    /**
     * 所属标签(#id#)便于查询
     */
    @Column(name = "tags")
    private String tags;

    /**
     * 评论数
     */
    @Column(name = "discuss")
    private Integer discuss;

    /**
     * 浏览数
     */
    @Column(name = "`view`")
    private Integer view;

    /**
     * 是否草稿（0：不是   1：是）
     */
    @Column(name = "draft_flag")
    private Boolean draftFlag;

    /**
     * 标签id集合（非数据库字段）
     */
    @Transient
    private Set<Long> tagIdList;

    /**
     * 标签集合（非数据库字段）
     */
    @Transient
    private Set<BlogTag> tagList;

    /**
     * 作者昵称（非数据库字段）
     */
    @Transient
    private String nickname;

    /**
     * 对tagId赋值的同时，也构造一下tags参数
     *
     * @param tagIdList
     */
    public void setTagIdList(Set<Long> tagIdList) {
        this.tagIdList = tagIdList;
        this.buildTagsStr();
    }

    /**
     * 构造tags参数
     *
     * @return
     */
    private void buildTagsStr() {
        if (CollectionUtils.isEmpty(this.tagIdList)) {
            this.tags = "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("#");
            this.tagIdList.forEach(tagId -> sb.append(tagId).append("#"));
            this.tags = sb.toString();
        }
    }

}