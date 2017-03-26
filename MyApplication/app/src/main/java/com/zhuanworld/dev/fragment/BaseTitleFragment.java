package com.zhuanworld.dev.fragment;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewStub;

import com.zhuanworld.dev.R;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public abstract class BaseTitleFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_title;
    }

    @Override
    protected void onBindViewBefore(View root) {
        super.onBindViewBefore(root);

        int stubC = getContentLayoutId();
        if (stubC > 0) {
            ViewStub stub = (ViewStub) root.findViewById(R.id.lay_content);
            stub.setLayoutResource(stubC);
            stub.inflate();
        }
    }

    protected abstract int getContentLayoutId();

}
