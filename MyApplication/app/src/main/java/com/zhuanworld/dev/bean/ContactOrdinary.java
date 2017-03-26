package com.zhuanworld.dev.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/24.
 */

public class ContactOrdinary implements Serializable {
    public String mContactName;
    public String mContactPhone;

    public ContactOrdinary(String contactName, String contactPhone) {
        mContactName = contactName;
        mContactPhone = contactPhone;
    }

}
