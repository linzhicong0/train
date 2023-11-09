package org.jack.common.util;

import cn.hutool.core.util.IdUtil;

public class SnowFlakeUtil {

    private static final long dataCenterId = 1;
    private static final long workerId = 1;


    public static long getSnowFlakeId() {
        return IdUtil.getSnowflake(dataCenterId, workerId).nextId();
    }
}
