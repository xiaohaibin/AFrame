package com.stx.xhb.aframe;

import android.content.DialogInterface;
import android.os.Bundle;

import com.xhb.aframelibrary.base.BaseActivity;

/**
 * @author: xiaohaibin.
 * @time: 2018/10/23
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class TranscetActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transcet;
    }

    @Override
    protected void initData(Bundle bundle) {
        android.support.v7.app.AlertDialog dialog = new android.support.v7.app.AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage("需要必要的权限才可以正常使用该功能，您已拒绝获得该权限。 如果需要重新授权，您可以点击“允许”按钮进入系统设置进行授权")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
    }
}
