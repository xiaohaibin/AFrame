package com.xhb.aframelibrary.mvp;

import com.xhb.aframelibrary.utils.CheckUtil;

import java.lang.ref.WeakReference;

/**
 * @author: xiaohaibin.
 * @time: 2019/5/6
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: BasePresenter
 */
public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V> {

    /**
     * 使用弱引用来防止内存泄漏
     */
    protected WeakReference<V> mRootView;
    protected M mModel;

    public BasePresenter(V rootView, M model) {
        CheckUtil.checkNotNull(model, "%s cannot be null", IModel.class.getName());
        CheckUtil.checkNotNull(rootView, "%s cannot be null", IView.class.getName());
        mRootView = new WeakReference<V>(rootView);
        mModel = model;
        onStart();
    }

    public BasePresenter(V rootView) {
        CheckUtil.checkNotNull(rootView, "%s cannot be null", IView.class.getName());
        mRootView = new WeakReference<V>(rootView);
        onStart();
    }

    @Override
    public void onStart() {}

    @Override
    public void onDestory() {
        if (mRootView != null) {
            mRootView.clear();
            mRootView = null;
        }
        if (mModel != null) {
            mModel.onDestroy();
            mModel = null;
        }
    }

    @Override
    public boolean isViewBind() {
        return mRootView != null;
    }

}
