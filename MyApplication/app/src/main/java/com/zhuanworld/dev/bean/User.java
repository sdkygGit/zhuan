package com.zhuanworld.dev.bean;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
public class User extends Author{

    // 本地缓存多余信息
    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
