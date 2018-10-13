package com.stx.xhb.aframe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.stx.xiaohaibin.aframelibrary.base.BaseActivity;
import com.stx.xiaohaibin.aframelibrary.widget.dialog.DialogMaker;

import butterknife.ButterKnife;
import butterknife.OnClick;

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
