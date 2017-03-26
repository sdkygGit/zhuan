package com.zhuanworld.dev.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/25.
 */

public class IncomeDetails implements Serializable {
    public String mDate;
    public String mIncome;

    public IncomeDetails(String date, String income) {
         mDate = date;
        mIncome = income;
    }
}
