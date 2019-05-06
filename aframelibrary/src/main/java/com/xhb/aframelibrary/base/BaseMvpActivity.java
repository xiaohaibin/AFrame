package com.xhb.aframelibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jaeger.library.StatusBarUtil;
import com.xhb.aframelibrary.R;
import com.xhb.aframelibrary.mvp.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: xiaohaibin.
 * @time: 2019/5/6
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: BaseMvpActivity
 */
public abstract class BaseMvpActivity<P extends IPresenter> extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;

    protected abstract P createPresenter();

    protected abstract int getLayoutResource();

    protected abstract void onInitData(Bundle bundle);

    /**
     * 如果当前页面逻辑简单, Presenter 可以为 null
     */
    @Nullable
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.common_main));
        try {
            int layoutResID = getLayoutResource();
            if (layoutResID != 0) {
                setContentView(layoutResID);
                mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        onInitData(savedInstanceState);
        mPresenter=createPresenter();
        if (mPresenter != null && mPresenter.isViewBind()) {
            mPresenter.onStart();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        if (mPresenter != null) {
            mPresenter.onDestory();
            mPresenter = null;
        }
    }
}
