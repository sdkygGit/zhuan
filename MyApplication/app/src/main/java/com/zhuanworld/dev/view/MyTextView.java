package com.zhuanworld.dev.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class MyTextView extends EditText {
    public MyTextView(Context context) {
        super(context);
        setTextIsSelectable(true);
        // TODO Auto-generated constructor stub
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        setTextIsSelectable(true);

    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        setTextIsSelectable(true);
    }

    @Override
    protected boolean getDefaultEditable() {//等同于在布局文件中设置 android:editable="false"
        return false;
    }
}
