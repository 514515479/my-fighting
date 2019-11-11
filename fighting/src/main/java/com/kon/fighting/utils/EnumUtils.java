package com.kon.fighting.utils;

import com.kon.fighting.common.enums.BaseEnum;

/**
 * @Description 枚举工具类
 * @Author LK
 * @Date 2019/8/7 10:00
 * @Version V1.0
 **/
public final class EnumUtils {

    /**
     * 该codedValue是否在此枚举中
     *
     * @param enums      枚举类型数组
     * @param codeValue 编码值
     * @return
     */
    public static BaseEnum codeValueIsInclude(BaseEnum[] enums, String codeValue) {
        if (codeValue == null) {
            return null;
        }
        for (BaseEnum b : enums) {
            if (codeValue.equals(b.getCodeValue())) {
                return b;
            }
        }
        return null;
    }

    /**
     * 该describe是否在此枚举中
     *
     * @param enums    枚举类型数组
     * @param describe 描述
     * @return
     */
    public static BaseEnum describeIsInclude(BaseEnum[] enums, String describe) {
        if (describe == null) {
            return null;
        }
        for (BaseEnum b : enums) {
            if (describe.equals(b.getDescribe())) {
                return b;
            }
        }
        return null;
    }

}
