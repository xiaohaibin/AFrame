package com.stx.xhb.aframe;

import android.content.Intent;
import android.os.Bundle;

import com.xhb.aframelibrary.base.BaseActivity;


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
//        DialogMaker.showProgressDialog(this, "加载中...");
        startActivity(new Intent(this,TranscetActivity.class));
    }


//    @Override
//    protected void setStatusBar() {
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
//    }
}
