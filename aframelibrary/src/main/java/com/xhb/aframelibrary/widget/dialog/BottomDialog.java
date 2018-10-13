package com.xhb.aframelibrary.widget.dialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.stx.xiaohaibin.aframelibrary.R;
import com.xhb.aframelibrary.base.BaseDialogFragment;

/**
 * @author xiaohaibin.
 * @time 2018/8/2
 * @mail xhb_199409@163.com
 * @github https://github.com/xiaohaibin
 * @describe BottomDialog
 */
public class BottomDialog extends BaseDialogFragment {

    private static final String KEY_LAYOUT_RES = "bottom_layout_res";
    private static final String KEY_HEIGHT = "bottom_height";
    private static final String KEY_DIM = "bottom_dim";
    private static final String KEY_CANCEL_OUTSIDE = "bottom_cancel_outside";

    private FragmentManager mFragmentManager;

    private boolean mIsCancelOutside = super.getCancelOutside();

    private String mTag = super.getFragmentTag();

    private float mDimAmount = super.getDimAmount();

    private int mHeight = super.getHeight();

    private ViewListener mViewListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
        if (savedInstanceState != null) {
            mLayoutRes = savedInstanceState.getInt(KEY_LAYOUT_RES);
            mHeight = savedInstanceState.getInt(KEY_HEIGHT);
            mDimAmount = savedInstanceState.getFloat(KEY_DIM);
            mIsCancelOutside = savedInstanceState.getBoolean(KEY_CANCEL_OUTSIDE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_LAYOUT_RES, mLayoutRes);
        outState.putInt(KEY_HEIGHT, mHeight);
        outState.putFloat(KEY_DIM, mDimAmount);
        outState.putBoolean(KEY_CANCEL_OUTSIDE, mIsCancelOutside);
        super.onSaveInstanceState(outState);
    }

    @LayoutRes
    private int mLayoutRes;

    @Override
    protected int getLayoutRes() {
        return mLayoutRes;
    }

    @Override
    public float getDimAmount() {
        return mDimAmount;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public boolean getCancelOutside() {
        return mIsCancelOutside;
    }

    @Override
    public String getFragmentTag() {
        return mTag;
    }

    @Override
    public void BindView(View v) {
        if (mViewListener != null) {
            mViewListener.bindView(v);
        }
    }

    public BottomDialog setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
        return this;
    }

    public BottomDialog setLayoutRes(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
        return this;
    }

    public BottomDialog setCancelOutside(boolean cancel) {
        mIsCancelOutside = cancel;
        return this;
    }

    public BottomDialog setTag(String tag) {
        mTag = tag;
        return this;
    }

    public BottomDialog setDimAmount(float dim) {
        mDimAmount = dim;
        return this;
    }

    public BottomDialog setHeight(int heightPx) {
        mHeight = heightPx;
        return this;
    }

    public BottomDialog setViewListener(ViewListener listener) {
        mViewListener = listener;
        return this;
    }


    public BaseDialogFragment show() {
        show(mFragmentManager);
        return this;
    }

    public interface ViewListener {
        void bindView(View view);
    }

}
