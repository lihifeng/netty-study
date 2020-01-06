package com.hifeng.netty.study.im.util;

import java.util.UUID;

/**
 * @author lzh
 */
public class IDUtils {

    private IDUtils() {}

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
