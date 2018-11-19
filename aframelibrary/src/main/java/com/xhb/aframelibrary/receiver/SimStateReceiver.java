package com.xhb.aframelibrary.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author: xiaohaibin.
 * @time: 2018/11/19
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 监听sim状态改变的广播，返回sim卡的状态， 有效或者无效。
 * 双卡中只要有一张卡的状态有效即返回状态为有效，两张卡都无效则返回无效。
 */
public class SimStateReceiver extends BroadcastReceiver {

    private static final String TAG = "SimStateReceive";
    public final static String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
    public final static int SIM_VALID = 0;
    public final static int SIM_INVALID = 1;
    private int simState = SIM_INVALID;

    public int getSimState() {
        return simState;
    }

    public SimStateReceiver() {
    }

    public SimStateReceiver(SimCardStateChangeListener simCardStateChangeListener) {
        mSimCardStateChangeListener = simCardStateChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_SIM_STATE_CHANGED)) {
            if (mSimCardStateChangeListener!=null) {
                mSimCardStateChangeListener.simCardStateChange();
            }
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            int state = tm.getSimState();
            switch (state) {
                case TelephonyManager.SIM_STATE_READY:
                    simState = SIM_VALID;
                    LogUtils.i(TAG, "sim state ：SIM_VALID");
                    break;
                case TelephonyManager.SIM_STATE_UNKNOWN:
                case TelephonyManager.SIM_STATE_ABSENT:
                case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                default:
                    LogUtils.i(TAG, "sim state ：SIM_INVALID");
                    simState = SIM_INVALID;
                    break;
            }
        }
    }

    public SimCardStateChangeListener mSimCardStateChangeListener;

    public interface SimCardStateChangeListener {
        void simCardStateChange();
    }
}
