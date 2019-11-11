package com.kon.fighting.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description TODO               
 * @Author LK
 * @Date 2019/11/8 14:48
 * @Version V1.0
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "blog_article_tag")
public class BlogArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 标签id
     */
    @Column(name = "tag_id")
    private Long tagId;
}