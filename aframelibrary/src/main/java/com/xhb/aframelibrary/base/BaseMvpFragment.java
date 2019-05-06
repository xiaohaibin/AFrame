package com.xhb.aframelibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhb.aframelibrary.mvp.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: xiaohaibin.
 * @time: 2019/5/6
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: BaseMvpFragment
 */
public abstract class BaseMvpFragment<P extends IPresenter> extends Fragment {
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

    /**
     * 是否可见
     */
    protected boolean isViable = false;

    /**
     * 标志位，标志Fragment已经初始化完成
     */
    protected boolean isPrepared = false;

    /**
     * 标记已加载完成，保证懒加载只能加载一次
     */
    protected boolean hasLoaded = false;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutResource() != -1) {
            rootView = inflater.inflate(getLayoutResource(), null);
        } else {
            rootView = super.onCreateView(inflater, container, savedInstanceState);
        }
        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
        }
        this.onInitData(savedInstanceState);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = createPresenter();
        if (mPresenter != null && mPresenter.isViewBind()) {
            mPresenter.onStart();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.onDestory();
            mPresenter = null;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isPrepared && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isViable = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
        hasLoaded = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView == null) {
            return;
        }
        isPrepared = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isViable = true;
            return;
        }
        if (isViable) {
            onFragmentVisibleChange(false);
            isViable = false;
        }
    }

    /**
     * 当界面可见时的操作
     */
    protected void onVisible() {
        if (hasLoaded) {
            return;
        }
        lazyLoad();
        hasLoaded = true;
    }


    /**
     * 数据懒加载
     */
    protected void lazyLoad() {
    }

    /**
     * 当界面不可见时的操作
     */
    protected void onInVisible() {
    }

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * @param isVisible
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            onVisible();
        } else {
            onInVisible();
        }
    }

}
