package com.xhb.aframelibrary.mvp;

/**
 * @author: xiaohaibin.
 * @time: 2019/5/6
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: IPresenter
 */
public interface IPresenter<V extends IView> {

    void onStart();

    void onDestory();

    boolean isViewBind();
}
