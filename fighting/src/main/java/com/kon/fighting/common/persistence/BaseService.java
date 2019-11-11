package com.kon.fighting.common.persistence;

import com.kon.fighting.common.dto.Page;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description 自己的Service接口
 * @Author LK
 * @Date 2019/10/31 17:54
 * @Version V1.0
 **/
public interface BaseService<T, Id> {

    /**
     * 保存
     */
    int save(T entity);


    /**
     * 选择性保存:null的属性不会保存，使用数据库默认值
     */
    int saveSelective(T entity);


    /**
     * 批量保存
     */
    int saveList(List<T> entityList);


    /**
     * 删除
     */
    int delete(T entity);


    /**
     * 根据id删除
     */
    int deleteById(Id id);


    /**
     * 批量删除
     */
    int deleteByIdList(List<Id> idList);


    /**
     * 根据条件删除
     */
    int deleteByExample(Example example);


    /**
     * 删除(伪删)
     */
    int deleteUse(T entity);


    /**
     * 根据id删除(伪删)
     */
    int deleteUseById(Id id);


    /**
     * 批量删除(伪删)
     */
    int deleteUseByIdList(List<Id> idList);


    /**
     * 根据条件删除(伪删)
     */
    int deleteUseByExample(Example example);


    /**
     * 更新
     */
    int update(T entity);


    /**
     * 选择性更新:null的属性不会保存，使用数据库默认值
     */
    int updateSelective(T entity);


    /**
     * 根据条件选择性更新:null的属性不会保存，使用数据库默认值
     */
    int updateByExampleSelective(T entity, Example example);


    /**
     * 通过id查询
     */
    T getById(Id id);


    /**
     * 条件查询单个实体
     */
    T getOne(T entity);


    /**
     * 条件查询单个实体
     */
    T getOneByExample(Example example);


    /**
     * 查询全部
     */
    List<T> getAll();


    /**
     * 条件查询
     */
    List<T> getByEntity(T entity);


    /**
     * 条件查询
     */
    List<T> getByExample(Example example);


    /**
     * 分页条件查询
     */
    Page<T> getPage(int pageNum, int pageSize, T entity);


    /**
     * 分页条件查询
     */
    Page<T> getPageByExample(int pageNum, int pageSize, Example example);

}
