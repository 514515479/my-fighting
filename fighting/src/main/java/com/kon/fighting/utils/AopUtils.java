package com.kon.fighting.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description Aop工具类
 * @Author LK
 * @Date 2019/10/22 15:51
 * @Version V1.0
 **/
public class AopUtils {

    /**
     * 构造参数Map
     *
     * @param parameters 参数名数组
     * @param args 参数值数组
     * @return
     */
    public static Map<String, Object> parametersMap(String[] parameters, Object[] args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            map.put(parameters[i], args[i]);
        }
        return map;
    }

    /**
     * 根据参数名寻找对应的下标index的值
     *
     * @param parameters 参数数组
     * @param paramName 参数名
     * @return 有则返回对应index，无则返回-1
     */
    public static int getParameterIndex(String[] parameters, String paramName) {
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].equals(paramName)) {
                return i;
            }
        }
        return -1;
    }

}
