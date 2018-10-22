package com.xhb.aframelibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author: xiaohaibin.
 * @time: 2018/10/22
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: CustomScrollView
 */
public class CustomScrollView extends ScrollView {

    private OnScollChangedListener onScollChangedListener;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScollChangedListener != null) {
            onScollChangedListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setOnScollChangedListener(OnScollChangedListener onScollChangedListener) {
        this.onScollChangedListener = onScollChangedListener;
    }

    public interface OnScollChangedListener {
        void onScrollChanged(int x, int y, int oldx, int oldy);
    }

}
