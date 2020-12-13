package com.wuwei.util;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 生成32位的UUID
     *
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
