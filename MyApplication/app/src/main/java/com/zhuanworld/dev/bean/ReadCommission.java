package com.zhuanworld.dev.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class ReadCommission implements Serializable {
    public String AmountMoney ;
    public String date;

    public ReadCommission(String amountMoney, String date) {
        AmountMoney = amountMoney;
        this.date = date;
    }
}
