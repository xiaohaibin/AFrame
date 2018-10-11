package com.stx.xiaohaibin.aframelibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jxnk25 on 2016/11/7.
 *
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description： 获取验证码按钮
 */
@SuppressLint("AppCompatCustomView")
public class GetSMSCodeButton extends TextView {

    private int seconds = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x10:
                    seconds--;
                    if (seconds == 0) {
                        setText("获取验证码");
                        setEnabled(true);
                    } else {
                        setText("重新获取" + seconds+"s");
                        handler.sendEmptyMessageDelayed(0x10, 1000);
                    }
                    break;
            }
        }
    };

    public GetSMSCodeButton(Context context) {
        super(context);
    }

    public GetSMSCodeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param seconds seconds秒后重试
     */
    public void disableIn(int seconds) {
        this.seconds = seconds;
        setEnabled(false);
        handler.removeCallbacksAndMessages(null);
        setText("验证码已发送");
        handler.sendEmptyMessageDelayed(0x10, 1000);
    }

}
