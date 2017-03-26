package com.zhuanworld.dev.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class BaiduCpc implements Serializable {
    public String imgUrl;
    public String title;

    public BaiduCpc(String imgUrl, String title) {
        this.imgUrl = imgUrl;
        this.title = title;
    }
}
