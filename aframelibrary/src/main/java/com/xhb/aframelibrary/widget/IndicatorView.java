package com.xhb.aframelibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import com.xhb.aframelibrary.R;


/**
 * 圆点指示器控件
 */
public class IndicatorView extends View {

    private int indicatorColor = Color.rgb(0, 0, 0);
    private int indicatorColorSelected = Color.rgb(0, 0, 0);
    private int indicatorWidth = 0;
    private int gravity = 0;
    private int indicatorCount = 0;
    private int currentIndicator = 0;
    private Paint mPaint;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x12) {
                invalidate();
            }
        }
    };

    public IndicatorView(Context context) {
        super(context);
        init();
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.IndicatorView);
            indicatorColor = typedArray.getColor(R.styleable.IndicatorView_indicatorColor, Color.rgb(0, 0, 0));
            indicatorColorSelected = typedArray.getColor(R.styleable.IndicatorView_indicatorColorSelected, Color.rgb(0, 0, 0));
            indicatorWidth = dp2px(context, typedArray.getInt(R.styleable.IndicatorView_indicatorWidth, 0));
            gravity = typedArray.getInt(R.styleable.IndicatorView_gravity, 0);
            typedArray.recycle();
        }
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int totalWidth = indicatorWidth * (2 * indicatorCount - 1);

        if (indicatorCount > 0) {
            for (int i = 0; i < indicatorCount; i++) {
                if (i == currentIndicator) {
                    mPaint.setColor(indicatorColorSelected);
                } else {
                    mPaint.setColor(indicatorColor);
                }
                int left = (viewWidth - totalWidth) / 2 + (i * 2 * indicatorWidth);
                switch (gravity) {
                    case 0:
                        left = (viewWidth - totalWidth) / 2 + (i * 2 * indicatorWidth);
                        break;
                    case 1:
                        left = i * 2 * indicatorWidth;
                        break;
                    case 2:
                        left = viewWidth - totalWidth + (i * 2 * indicatorWidth);
                        break;
                    default:
                        break;
                }
                int top = (viewHeight - indicatorWidth) / 2;
                int right = left + indicatorWidth;
                int bottom = top + indicatorWidth;
                RectF rectF = new RectF(left, top, right, bottom);
                canvas.drawOval(rectF, mPaint);
            }
        }
    }

    public void setIndicatorCount(int indicatorCount) {
        this.indicatorCount = indicatorCount;
    }

    public void setCurrentIndicator(int currentIndicator) {
        this.currentIndicator = currentIndicator;
        handler.sendEmptyMessage(0x12);
    }

    public static int dp2px(Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
