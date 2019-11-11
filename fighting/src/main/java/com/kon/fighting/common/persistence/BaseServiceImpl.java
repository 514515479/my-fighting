package com.kon.fighting.common.persistence;

import com.github.pagehelper.PageHelper;
import com.kon.fighting.common.dto.Page;
import com.kon.fighting.utils.ReflectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Description 自己的Service接口实现
 * @Author LK
 * @Date 2019/10/31 17:55
 * @Version V1.0
 **/
public abstract class BaseServiceImpl<T, Id> implements BaseService<T, Id> {

    protected abstract BaseMapper<T> getMapper();

    /**
     * 保存
     */
    @Override
    public int save(T entity) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setCreate();
        }
        return getMapper().insert(entity);
    }


    /**
     * 选择性保存:null的属性不会保存，使用数据库默认值
     */
    @Override
    public int saveSelective(T entity) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setCreate();
        }
        return getMapper().insertSelective(entity);
    }


    /**
     * 批量保存
     */
    @Override
    public int saveList(List<T> entityList) {
        if (entityList.get(0) instanceof BaseEntity) {
            entityList.forEach(entity -> ((BaseEntity) entity).setCreate());
        }
        return getMapper().insertList(entityList);
    }


    /**
     * 删除
     */
    @Override
    public int delete(T entity) {
        return getMapper().delete(entity);
    }


    /**
     * 根据id删除
     */
    @Override
    public int deleteById(Id id) {
        return getMapper().deleteByPrimaryKey(id);
    }


    /**
     * 批量删除
     */
    @Override
    public int deleteByIdList(List<Id> ids) {
        Class<T> tClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Example example = new Example(tClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        return getMapper().deleteByExample(example);
    }


    /**
     * 根据条件删除
     */
    @Override
    public int deleteByExample(Example example) {
        return getMapper().deleteByExample(example);
    }


    /**
     * 删除(伪删)
     * 这个方法必须继承BaseEntity
     */
    @Override
    public int deleteUse(T entity) {
        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setDelFlag(true);
            baseEntity.setUpdate();
            return this.updateSelective(entity);
        }
        return 0;
    }


    /**
     * 根据id删除(伪删)
     * 这个方法必须继承BaseEntity
     */
    @Override
    public int deleteUseById(Id id) {
        BaseEntity<Id> baseEntity = BaseServiceUtils.getBaseEntityByServiceClass(this.getClass());
        if (baseEntity == null) {
            return 0;
        } else {
            baseEntity.setId(id);
            baseEntity.setDelFlag(true);
            baseEntity.setUpdate();
            return this.updateSelective((T) baseEntity);
        }
    }


    /**
     * 批量删除(伪删)
     * 这个方法必须继承BaseEntity
     */
    @Override
    public int deleteUseByIdList(List<Id> ids) {
        Class<T> cClass = (Class<T>) ReflectionUtils.getSuperClassGenericParameterizedType(this.getClass());
        BaseEntity<Id> baseEntity = BaseServiceUtils.getBaseEntityByChildClass(cClass);
        if (baseEntity == null) {
            return 0;
        } else {
            baseEntity.setDelFlag(true);
            baseEntity.setUpdate();
            Example example = new Example(cClass);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("id", ids);
            return this.updateByExampleSelective((T) baseEntity, example);
        }
    }


    /**
     * 根据条件删除(伪删)
     * 这个方法必须继承BaseEntity
     */
    @Override
    public int deleteUseByExample(Example example) {
        BaseEntity<Id> baseEntity = BaseServiceUtils.getBaseEntityByServiceClass(this.getClass());
        if (baseEntity == null) {
            return 0;
        } else {
            baseEntity.setDelFlag(true);
            baseEntity.setUpdate();
            return this.updateByExampleSelective((T) baseEntity, example);
        }
    }


    /**
     * 更新
     */
    @Override
    public int update(T entity) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setUpdate();
        }
        return getMapper().updateByPrimaryKey(entity);
    }


    /**
     * 选择性更新:null的属性不会保存，使用数据库默认值
     */
    @Override
    public int updateSelective(T entity) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setUpdate();
        }
        return getMapper().updateByPrimaryKeySelective(entity);
    }


    /**
     * 根据条件选择性更新:null的属性不会保存，使用数据库默认值
     */
    @Override
    public int updateByExampleSelective(T entity, Example example) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setUpdate();
        }
        return getMapper().updateByExampleSelective(entity, example);
    }


    /**
     * 通过id查询
     */
    @Override
    public T getById(Id id) {
        return getMapper().selectByPrimaryKey(id);
    }

    /**
     * 条件查询单个实体
     */
    @Override
    public T getOne(T entity) {
        return getMapper().selectOne(entity);
    }


    /**
     * 条件查询单个实体
     */
    @Override
    public T getOneByExample(Example example) {
        return getMapper().selectOneByExample(example);
    }


    /**
     * 查询全部
     */
    @Override
    public List<T> getAll() {
        return getMapper().selectAll();
    }


    /**
     * 条件查询
     */
    @Override
    public List<T> getByEntity(T entity) {
        return getMapper().select(entity);
    }


    /**
     * 条件查询
     */
    @Override
    public List<T> getByExample(Example example) {
        return getMapper().selectByExample(example);
    }


    /**
     * 分页条件查询
     */
    @Override
    public Page<T> getPage(int pageNum, int pageSize, T entity) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> selectList = getMapper().select(entity);
        return Page.pageData(selectList);
    }


    /**
     * 分页条件查询
     */
    @Override
    public Page<T> getPageByExample(int pageNum, int pageSize, Example example) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> selectList = getMapper().selectByExample(example);
        return Page.pageData(selectList);
    }

}
