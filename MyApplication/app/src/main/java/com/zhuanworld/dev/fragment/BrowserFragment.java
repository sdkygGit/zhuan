package com.zhuanworld.dev.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zhuanworld.dev.AppConfig;
import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.BackActivity;
import com.zhuanworld.dev.activity.BaseActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/13 0013.
 */
public class BrowserFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.webview)
    WebView mWebView;
    @Bind(R.id.browser_back)
    ImageView mImgBack;
    @Bind(R.id.browser_forward)
    ImageView mImgForward;
    @Bind(R.id.browser_refresh)
    ImageView mImgRefresh;
    @Bind(R.id.browser_system_browser)
    ImageView mImgSystemBrowser;
    @Bind(R.id.browser_bottom)
    LinearLayout mLayoutBottom;
    @Bind(R.id.progress)
    ProgressBar mProgress;
    @Bind(R.id.error)
    View error;

    public static final String DEFAULT = "http://";
    private String mCurrentUrl = DEFAULT;
    private Activity aty;
    public static final String BROWSER_KEY = "browser_url";

    private Animation animBottomIn, animBottomOut;
    private GestureDetector mGestureDetector;
    private int TAG = 1; // 双击事件需要

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_browser;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.browser_back:
                mWebView.goBack();
                break;
            case R.id.browser_forward:
                mWebView.goForward();
                break;
            case R.id.browser_refresh:
                mWebView.loadUrl(mWebView.getUrl());
                break;
            case R.id.browser_system_browser:
                try {
                    // 启用外部浏览器
                    Uri uri = Uri.parse(mCurrentUrl);
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    aty.startActivity(it);
                } catch (Exception e) {
                    AppContext.showToast("网页地址错误");
                }
                break;
        }
    }

    @Override
    protected void initData() {

        Bundle bundle = getActivity().getIntent().getBundleExtra(
                BackActivity.BUNDLE_KEY_ARGS);
        if (bundle != null) {
            mCurrentUrl = bundle.getString(BROWSER_KEY);
        }

        mGestureDetector = new GestureDetector(aty, new MyGestureListener());

        mWebView.loadUrl(mCurrentUrl);
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        });
    }

    /**
     * 初始化浏览器设置信息
     */
    private void initWebView() {
//        cookie = CookieManager.getInstance();
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用支持javascript
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 优先使用缓存
        webSettings.setAllowFileAccess(true);// 可以访问文件
        webSettings.setBuiltInZoomControls(true);// 支持缩放
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            webSettings.setPluginState(WebSettings.PluginState.ON);
            webSettings.setDisplayZoomControls(false);// 支持缩放
        }
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
    }

    @Override
    protected void initWidget(View root) {
        aty = (Activity) mContext;

        initWebView();
        initBarAnim();

        mImgBack.setOnClickListener(this);
        mImgForward.setOnClickListener(this);
        mImgRefresh.setOnClickListener(this);
        mImgSystemBrowser.setOnClickListener(this);
    }

    /**
     * 初始化上下栏的动画并设置结束监听事件
     */
    private void initBarAnim() {
        animBottomIn = AnimationUtils.loadAnimation(aty, R.anim.anim_bottom_in);
        animBottomOut = AnimationUtils.loadAnimation(aty,
                R.anim.anim_bottom_out);
        animBottomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLayoutBottom.setVisibility(View.VISIBLE);
            }
        });
        animBottomOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLayoutBottom.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 载入链接之前会被调用
     *
     * @param view WebView
     * @param url  链接地址
     */
    protected void onUrlLoading(WebView view, String url) {
        mProgress.setVisibility(View.VISIBLE);
//        cookie.setCookie(url,
//                AppContext.getInstance().getProperty(AppConfig.CONF_COOKIE));
    }

    /**
     * 链接载入成功后会被调用
     *
     * @param view WebView
     * @param url  链接地址
     */
    protected void onUrlFinished(WebView view, String url) {
        mCurrentUrl = url;
        mProgress.setVisibility(View.GONE);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            onUrlLoading(view, url);
            view.loadUrl(url);
            boolean flag = super.shouldOverrideUrlLoading(view, url);
            mCurrentUrl = url;
            return flag;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (error.getVisibility() != View.GONE)
                error.setVisibility(View.GONE);
        }

        //        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//            onUrlFinished(view, url);
//        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            Toast.makeText(aty, "onReceivedError", Toast.LENGTH_SHORT).show();
            error.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 当前WebView显示页面的标题
     *
     * @param view  WebView
     * @param title web页面标题
     */
    protected void onWebTitle(WebView view, String title) {
        if (aty != null && mWebView != null) { // 必须做判断，由于webview加载属于耗时操作，可能会本Activity已经关闭了才被调用
            ((BackActivity) aty).setTitleText(mWebView.getTitle());
        }
    }

    /**
     * 当前WebView显示页面的图标
     *
     * @param view WebView
     * @param icon web页面图标
     */
    protected void onWebIcon(WebView view, Bitmap icon) {
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            onWebTitle(view, title);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
            onWebIcon(view, icon);
        }


        @Override
        public void onProgressChanged(WebView view, int newProgress) { // 进度
            super.onProgressChanged(view, newProgress);
            if (newProgress > 90) {
                mProgress.setVisibility(View.GONE);
            }
        }
    }


    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTap(MotionEvent e) {// webview的双击事件
            if (TAG % 2 == 0) {
                TAG++;
                mLayoutBottom.startAnimation(animBottomIn);
            } else {
                TAG++;
                mLayoutBottom.startAnimation(animBottomOut);
            }
            return super.onDoubleTap(e);
        }
    }
}
