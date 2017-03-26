package com.zhuanworld.dev.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhuanworld.dev.R;

/**
 * Created by Administrator on 2017/3/20 0020.
 */
public class EmptyLayout extends LinearLayout implements View.OnClickListener {

    private android.view.View.OnClickListener listener;
    public ImageView img;
    private TextView tv;
    private ProgressBar pb;

    public EmptyLayout(Context context) {
        this(context, null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_error_layout, this, false);
        img = (ImageView) view.findViewById(R.id.img);
        pb = (ProgressBar) view.findViewById(R.id.animProgress);
        tv = (TextView) view.findViewById(R.id.tv);
        setOnClickListener(this);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // setErrorType(NETWORK_LOADING);
//                if (listener != null)
//                    listener.onClick(v);
//            }
//        });
        addView(view);
    }

    public void setOnLayoutClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

    public void setErrorMessage(String msg) {
        tv.setText(msg);
    }

}
