package com.zhuanworld.dev.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18 0018.
 */
public class Folder implements Serializable {
    public String name;
    public String path;

    List<Image> images;

    public Folder(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
