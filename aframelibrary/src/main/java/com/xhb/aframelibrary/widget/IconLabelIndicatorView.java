package com.xhb.aframelibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xhb.aframelibrary.R;


/**
 * 带icon的label标签
 *
 * @author Mr.xiao
 */
public class IconLabelIndicatorView extends ViewGroup {

    private View contentView;
    private ImageView iconImageView;
    private TextView labelTextView;
    private ImageView indicatorImageView;

    public IconLabelIndicatorView(Context context) {
        super(context);
        init(null);
    }

    public IconLabelIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        contentView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        contentView.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    private void init(AttributeSet attrs) {
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.layout_widget_icon_label_indicator_view, null);
        iconImageView = contentView.findViewById(R.id.icon);
        labelTextView = contentView.findViewById(R.id.label);
        indicatorImageView = contentView.findViewById(R.id.indicator);
        addView(contentView);

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.IconLabelIndicatorView);
            iconImageView.setImageResource(typedArray.getResourceId(R.styleable.IconLabelIndicatorView_viewIcon, 0));
            labelTextView.setText(typedArray.getString(R.styleable.IconLabelIndicatorView_viewLabel));
            indicatorImageView.setImageResource(typedArray.getResourceId(R.styleable.IconLabelIndicatorView_viewIndicator, 0));
            typedArray.recycle();
        }
    }

}
