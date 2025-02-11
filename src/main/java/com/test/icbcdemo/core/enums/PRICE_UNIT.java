package com.test.icbcdemo.core.enums;

/**
 * 單位枚舉表
 * 以克(g)為單位定義枚舉值
 */
public enum PRICE_UNIT {

    G(1), //1克
    KG(1000); //公斤

    //單位
    private final int unit;

    PRICE_UNIT(int unit){
        this.unit = unit;
    }

    public int getUnit(){
        return unit;
    }


}
