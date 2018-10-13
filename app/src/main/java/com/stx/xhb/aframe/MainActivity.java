package com.stx.xhb.aframe;

import android.os.Bundle;

import com.xhb.aframelibrary.base.BaseActivity;
import com.xhb.aframelibrary.widget.dialog.DialogMaker;

import butterknife.OnClick;

/**
 * @author Mr.xiao
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        DialogMaker.showProgressDialog(this, "加载中...");
    }

}
