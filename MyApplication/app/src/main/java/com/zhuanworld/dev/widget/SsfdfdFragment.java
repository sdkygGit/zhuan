package com.zhuanworld.dev.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/3/11 0011.
 */
public class SsfdfdFragment extends FrameLayout {

    public SsfdfdFragment(Context context) {
        super(context);
    }

    public SsfdfdFragment(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SsfdfdFragment(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightSize == 0) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
            return;
        }

        if (widthSize == 0) {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
            return;
        }

        if (widthSize > heightSize)
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        else
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
