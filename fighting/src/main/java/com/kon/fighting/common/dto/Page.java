package com.kon.fighting.common.dto;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 自定义分页对象
 * @Author LK
 * @Date 2019/10/16 16:26
 * @Version V1.0
 **/
@Data
@Accessors(chain = true)
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -1450172761429708978L;

    /**
     * 当前页数
     */
    private int pageNum;

    /**
     * 页大小
     */
    private int pageSize;

    /**
     * 总页数
     */
    private long totalSize;

    /**
     * 分页数据
     */
    private List<T> list;

    /**
     * 构造自定义分页对象
     *
     * @param pageList 分页数据
     * @param <T>      泛型
     * @return
     */
    public static <T> Page<T> pageData(List<T> pageList) {
        PageInfo<T> pageInfo = new PageInfo<>(pageList);
        return new Page<T>()
                .setPageNum(pageInfo.getPageNum())
                .setPageSize(pageInfo.getPageSize())
                .setTotalSize(pageInfo.getTotal())
                .setList(pageInfo.getList());
    }

}
