package com.wuwei.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuwei
 * @date 2018/7/3 15:20
 * @description Java object 与 map之间的相互转换
 */
public class ObjectUtil {

    /**
     * object转map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, String> objectToMap(Object obj) throws Exception {
        if (obj == null) return null;
        Map<String, String> map = new HashMap<>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value != null && field.getType() == Date.class) {
                Date date = (Date) value;
                value = date.getTime();
            }
            map.put(field.getName(), value == null ? "" : String.valueOf(value));
        }
        return map;
    }

    /**
     * map转object
     *
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, String> map, Class<?> beanClass) throws Exception {
        if (map == null || map.isEmpty()) return null;
        Object obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            Object data;
            String value = map.get(field.getName());
            if (field.getType() == Integer.class) {
                data = "".equals(value) ? null : Integer.parseInt(value);
            } else if (field.getType() == Date.class) {
                data = "".equals(value) ? null : new Date(Long.parseLong(value));
            } else {
                data = value;
            }
            field.set(obj, data);
        }
        return obj;
    }
}
