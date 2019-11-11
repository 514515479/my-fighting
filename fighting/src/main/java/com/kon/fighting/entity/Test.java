package com.kon.fighting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kon.fighting.common.persistence.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Description 测试实体类
 * @Author LK
 * @Date 2019/11/1 12:14
 * @Version V1.0
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "test")
public class Test extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 大数值
     */
    @Column(name = "`bigint`")
    private Long bigint;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 日期
     */
    @Column(name = "`date`")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    /**
     * 具体时间
     */
    @Column(name = "`datetime`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date datetime;

}