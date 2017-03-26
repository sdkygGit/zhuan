package com.zhuanworld.dev.callback;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public interface PermissionGrantedCallBack {
    void onPermissionsDenied(int requestCode, List<String> perms);
}
