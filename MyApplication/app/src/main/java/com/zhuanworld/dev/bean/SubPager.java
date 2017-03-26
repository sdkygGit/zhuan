package com.zhuanworld.dev.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public class SubPager implements Serializable {

    public String img;
    public String href;

    public SubPager(String img, String href) {
        this.img = img;
        this.href = href;
    }
}
