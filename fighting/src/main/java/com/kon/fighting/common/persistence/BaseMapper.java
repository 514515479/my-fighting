package com.kon.fighting.common.persistence;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description 自己的Mapper(特别注意，该接口不能被扫描到，否则会出错)
 * @Author LK
 * @Date 2019/10/31 17:36
 * @Version V1.0
 **/
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
