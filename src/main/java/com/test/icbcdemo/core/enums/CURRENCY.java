package com.test.icbcdemo.core.enums;

/**
 * 單位枚舉表
 * 以克(g)為單位定義枚舉值
 */
public enum CURRENCY {

    HKD("HKD"), //港幣
    RMB("RMB"), //人民幣
    MOP("MOP"); //澳門遠

    //單位
    private final String currency;

    CURRENCY(String currency){
        this.currency = currency;
    }

    public String getUnit(){
        return currency;
    }


}
