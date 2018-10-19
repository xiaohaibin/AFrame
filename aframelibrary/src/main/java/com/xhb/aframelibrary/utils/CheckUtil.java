package com.xhb.aframelibrary.utils;

import android.support.annotation.Nullable;

/**
 * @author: xiaohaibin.
 * @time: 2018/10/19
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class CheckUtil {

    public static <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
