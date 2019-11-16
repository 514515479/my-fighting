package com.kon.fighting.service;

import com.kon.fighting.common.constant.CustomStaticValue;
import com.kon.fighting.common.dto.Page;
import com.kon.fighting.common.persistence.BaseMapper;
import com.kon.fighting.common.persistence.BaseServiceImpl;
import com.kon.fighting.entity.BlogTag;
import com.kon.fighting.mapper.BlogTagMapper;
import com.kon.fighting.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO               
 * @Author LK
 * @Date 2019/11/8 14:48
 * @Version V1.0
 **/
@Slf4j
@Service
public class BlogTagService extends BaseServiceImpl<BlogTag, Long> {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    protected BaseMapper<BlogTag> getMapper() {
        return this.blogTagMapper;
    }

    /**
     * 查询分页数据
     *
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    public Page<BlogTag> findPageByMap(int pageNum, int pageSize, Map<String, Object> map) {
        Example example = new Example(BlogTag.class);
        Example.Criteria criteria = example.createCriteria();
        // 判断是否个人模式
        if (CustomStaticValue.INDIVIDUAL_MODEL) {
            criteria.andEqualTo("createUser", SecurityUtils.getUser().getId());
        }
        // 模糊查询
        String name = (String) map.get("name");
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        example.orderBy("sort").asc();
        return this.getPageByExample(pageNum, pageSize, example);
    }

    /**
     * 查询所有标签(根据sort排序)
     * @return
     */
    public List<BlogTag> findAllTag() {
        Example example = new Example(BlogTag.class);
        example.orderBy("sort").asc();
        return blogTagMapper.selectByExample(example);
    }

}
