package com.xhb.aframelibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/6
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: BaseFragment
 */
public abstract class BaseFragment extends Fragment {

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

    /**
     * 当前页码
     */
    protected int currentpage = 1;

    /**
     * 页面数据量
     */
    protected int pageSize = 10;
    protected View rootView;
    protected Context mContext = null;

    protected abstract int getLayoutResource();

    protected void initView() {
    }

    protected void setListener() {
    }

    protected abstract void initData(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutResource() != 0) {
            rootView = inflater.inflate(getLayoutResource(), null);
        } else {
            rootView = super.onCreateView(inflater, container, savedInstanceState);
        }
        ButterKnife.bind(this, rootView);
        initData(savedInstanceState);
        initView();
        setListener();
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isPrepared && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isViable = true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
        hasLoaded = false;
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     *
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
     *
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
