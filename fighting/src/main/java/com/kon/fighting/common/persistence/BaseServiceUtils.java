package com.kon.fighting.common.persistence;

import com.kon.fighting.utils.ReflectionUtils;

/**
 * @Description 通用service工具类
 * @Author LK
 * @Date 2019/11/1 13:54
 * @Version V1.0
 **/
public class BaseServiceUtils {

    /**
     * 通过接口类获取BaseEntity子类的实例
     *
     * @param sClass 接口
     * @return
     */
    public static BaseEntity getBaseEntityByServiceClass(Class<?> sClass) {
        Class<?> cClass = ReflectionUtils.getSuperClassGenericParameterizedType(sClass);
        return getBaseEntityByChildClass(cClass);
    }

    /**
     * 通过子类获取BaseEntity子类的实例
     *
     * @param cClass 子类
     * @return
     */
    public static BaseEntity getBaseEntityByChildClass(Class<?> cClass) {
        BaseEntity baseEntity = null;
        try {
            Object obj = cClass.newInstance();
            if (obj instanceof BaseEntity) {
                baseEntity = (BaseEntity) obj;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return baseEntity;
    }

}
