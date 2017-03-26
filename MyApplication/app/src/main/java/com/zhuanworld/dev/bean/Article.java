package com.zhuanworld.dev.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public class Article implements Serializable {
    public String imgPath;
    public String title;

    public Article(String imgPath, String title) {
        this.imgPath = imgPath;
        this.title = title;
    }
}
