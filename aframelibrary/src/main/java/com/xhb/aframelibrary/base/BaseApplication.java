package com.xhb.aframelibrary.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * @author: xiaohaibin.
 * @time: 2018/10/12
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: BaseApplication
 */
public class BaseApplication extends Application {

    private static BaseApplication ourInstance;

    public static BaseApplication getInstance() {
        return ourInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance=this;
        Utils.init(this);
    }
}
