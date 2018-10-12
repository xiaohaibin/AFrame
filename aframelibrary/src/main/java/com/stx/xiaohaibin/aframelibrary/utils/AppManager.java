package com.stx.xiaohaibin.aframelibrary.utils;

import android.app.Activity;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;

/**
 * link https://xiaohaibin.github.io/
 * email： xhb_199409@163.com
 * github: https://github.com/xiaohaibin
 * describe: Activity管理工具类
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * @return 获取activity管理实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (activityStack != null && !activityStack.empty()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        if (activityStack != null && !activityStack.empty()) {
            Activity activity = activityStack.lastElement();
            killActivity(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null && activityStack != null && !activityStack.empty()) {
            activity.finish();
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        if (activityStack != null && !activityStack.empty()) {
            Iterator<Activity> iterator = activityStack.iterator();
            while (iterator.hasNext()) {
                Activity activity = iterator.next();
                if (activity.getClass().equals(cls)) {
                    activity.finish();
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        while (activityStack != null && !activityStack.empty()) {
            Activity activity = currentActivity();
            killActivity(activity);
            activityStack.clear();
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            killAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception ignored) {
        }
    }

    public int getSize() {
        return activityStack.size();
    }

}
