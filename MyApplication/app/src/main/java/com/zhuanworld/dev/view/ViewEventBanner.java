package com.zhuanworld.dev.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.RequestManager;
import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.SubPager;
import com.zhuanworld.dev.bean.SubTab;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public class ViewEventBanner extends RelativeLayout implements View.OnClickListener {
    private SubPager banner;
    private ImageView mImageEnent;

    public ViewEventBanner(Context context) {
        this(context, null);
    }

    public ViewEventBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewEventBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.banner_item, this, true);
        mImageEnent = (ImageView) findViewById(R.id.iv_event);
        setOnClickListener(this);
    }

    public void setDefaultImage(){
        mImageEnent.setImageResource(R.mipmap.event_cover_default);
    }

    public void initData(RequestManager manager, SubPager banner) {
        this.banner = banner;
//        manager.load(banner.img)
//                .placeholder(R.mipmap.event_cover_default)
//                .error(R.mipmap.event_cover_default)
//                .into(mImageEnent);
        manager.load(banner.img)
                .placeholder(R.mipmap.lunboguanggao_test)
                .error(R.mipmap.lunboguanggao_test)
                .into(mImageEnent);
    }

    @Override
    public void onClick(View v) {
        if (banner != null)
            AppContext.showToast(banner.href);
    }
}
