package com.kon.fighting.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description 集合工具类
 * @Author LK
 * @Date 2019/10/21 14:54
 * @Version V1.0
 **/
public class CollectionUtils {

    public static boolean isEmpty(Collection list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNoEmpty(Collection list) {
        return !isEmpty(list);
    }

    /**
     * @param map 取值的集合
     * @param key 所想取值的集合的key
     * @return 返回key对应的value
     */
    public static String getMapValue(Map<String, Object> map, String key) {
        String result = null;
        if (map != null) {
            Iterator<String> iterable = map.keySet().iterator();
            while (iterable.hasNext()) {
                Object object = iterable.next();
                if (key.equals(object))
                    if (map.get(object) != null)
                        result = map.get(object).toString();
            }
        }
        return result;
    }

}
