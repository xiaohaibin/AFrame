package com.xhb.aframelibrary.base;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * author: xiaohaibin.
 * time: 2018/7/31
 * mail:xhb_199409@163.com
 * github:https://github.com/xiaohaibin
 * describe: BaseDialogFragment
 */
public abstract class BaseDialogFragment extends DialogFragment {
    protected View contentView;
    private static final String TAG = "BaseDialogFragment";
    private static final float DEFAULT_DIM = 0.2f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(getCancelOutside());
        setContentView();
        BindView(contentView);
        registerViews();
        return contentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        window.setGravity(getGravity());
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        params.dimAmount = getDimAmount();
        params.width = getWidth();
        if (getHeight() > 0) {
            params.height = getHeight();
        } else {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        window.setAttributes(params);
    }

    public int getHeight() {
        return -1;
    }

    public int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    protected int getGravity() {
        return Gravity.CENTER;
    }

    protected boolean getCancelOutside() {
        return true;
    }

    public float getDimAmount() {
        return DEFAULT_DIM;
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    private void setContentView() {
        contentView = LayoutInflater.from(getActivity()).inflate(getLayoutRes(), null);
    }

    public View findViewById(@IdRes int id) {
        return contentView.findViewById(id);
    }

    public abstract void BindView(View v);

    /**
     * 省略findViewById的强制类型转换
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }

    protected void initViews() {
    }

    protected void registerViews() {
    }

    public String getFragmentTag() {
        return TAG;
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, getFragmentTag());
    }


    public interface onDismissListener {
        void onDismiss();
    }

    public onDismissListener mOnDismissListener;

    public void setOnDismissListener(onDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener!=null){
            mOnDismissListener.onDismiss();
        }
    }

    /**
     * 判断对话框是否显示
     * @return
     */
    public boolean isShow() {
        if (getDialog() != null) {
            return getDialog().isShowing();
        }
        return false;
    }

}

