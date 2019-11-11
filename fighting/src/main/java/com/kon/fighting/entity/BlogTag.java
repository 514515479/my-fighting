package com.kon.fighting.entity;

import com.kon.fighting.common.persistence.BaseEntity;
import java.io.Serializable;
import java.util.Date;
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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Table(name = "blog_tag")
public class BlogTag extends BaseEntity<Long> implements Serializable {

    /**
     * 标签名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

}