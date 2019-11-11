package com.kon.fighting.common.enums;

/**
 * @Description 枚举值类型(枚举类继承该类，就可以使用EnumUtils)
 * @Author LK
 * @Date 2019/8/7 9:58
 * @Version V1.0
 **/
public interface BaseEnum {

    /**
     * 获取编码值
     *
     * @return
     */
    String getCodeValue();

    /**
     * 获取描述
     *
     * @return
     */
    String getDescribe();

}
