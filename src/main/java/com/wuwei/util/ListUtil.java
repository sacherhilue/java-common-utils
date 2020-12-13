package com.wuwei.util;

import java.util.List;

/**
 * @author wuwei
 * @date 2018/7/6 15:46
 * @description 将String类型的List转为字符串
 */
public class ListUtil {

    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String str : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(str);
        }
        return result.toString();
    }
}
