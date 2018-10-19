package com.stx.xhb.aframe;

import android.app.Application;

import com.xhb.aframelibrary.http.HttpManager;

/**
 * @author: xiaohaibin.
 * @time: 2018/10/19
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpManager.getInstance().init("",BuildConfig.DEBUG);
    }
}
