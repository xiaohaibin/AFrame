package com.xhb.aframelibrary.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author: xiaohaibin.
 * @time: 2018/10/25
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 可以禁止左右滑动的ViewPager
 */
public class ViewPagerSlide extends ViewPager {

    /**
     * 是否可以进行滑动
     */
    private boolean isSlide = false;

    public void setSlide(boolean slide) {
        isSlide = slide;
    }
    public ViewPagerSlide(Context context) {
        super(context);
    }

    public ViewPagerSlide(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide;
    }
}
