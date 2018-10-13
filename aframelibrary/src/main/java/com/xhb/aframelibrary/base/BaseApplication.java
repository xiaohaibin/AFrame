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

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
