package com.stx.xhb.aframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stx.xiaohaibin.aframelibrary.widget.dialog.DialogMaker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogMaker.showProgressDialog(this,"加载中...");
    }
}
