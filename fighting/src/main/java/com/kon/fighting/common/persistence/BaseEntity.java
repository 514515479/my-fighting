package com.kon.fighting.common.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kon.fighting.entity.SuperUser;
import com.kon.fighting.utils.SecurityUtils;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 实体类的基类
 * @Author LK
 * @Date 2019/11/1 10:09
 * @Version V1.0
 **/
@Data
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private T id;

    /**
     * 数据创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "`create_time`")
    private LocalDateTime createTime;

    /**
     * 数据创建者
     */
    @JsonIgnore
    @Column(name = "`create_user`")
    private Long createUser;

    /**
     * 数据修改时间
     */
    @JsonIgnore
    @Column(name = "`update_time`")
    private LocalDateTime updateTime;

    /**
     * 数据修改者
     */
    @JsonIgnore
    @Column(name = "`update_user`")
    private Long updateUser;

    /**
     * 数据有效位(0:有效 1:删除)
     */
    @JsonIgnore
    @Column(name = "`del_flag`")
    private Boolean delFlag;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    /**
     * 设置创建时间和创建人
     */
    public void setCreate() {
        SuperUser superUser = SecurityUtils.getUser();
        if (superUser != null) {
            this.createTime = LocalDateTime.now();
            this.setCreateUser(superUser.getId());
        }
    }

    /**
     * 设置更新时间和更新人
     */
    public void setUpdate() {
        SuperUser superUser = SecurityUtils.getUser();
        if (superUser != null) {
            this.updateTime = LocalDateTime.now();
            this.setUpdateUser(superUser.getId());
        }
    }

}
