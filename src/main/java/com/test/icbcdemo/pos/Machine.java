package com.test.icbcdemo.pos;

import com.test.icbcdemo.core.Product;
import com.test.icbcdemo.core.enums.CURRENCY;

import java.util.Currency;
import java.util.Date;

public interface Machine {
    /**
     * 從pos機中移除
     *
     * @param product
     * @return
     */
    boolean removeProduct(Product product);

    /**
     * 啟動機器-普通啟動
     *
     * @param currency    幣種
     * @return
     */
    void start(CURRENCY currency);
    /**
     * 啟動機器 - 折扣啟動
     *
     * @param startDate   打折開始時間
     * @param endDate     打折結束時間
     * @param currency    幣種
     * @return
     */
    void startWithDisount(Date startDate, Date endDate, CURRENCY currency);
    /**
     * 啟動機器 - 促銷啟動
     *
     * @param spend       滿價
     * @param offer       滿減
     * @param currency    幣種
     * @return
     */
    void startWithPromotion(double spend, double offer, CURRENCY currency);

    /**
     * 啟動機器 - 折扣與促銷啟動
     *
     * @param startDate   打折開始時間
     * @param endDate     打折結束時間
     * @param spend       滿價
     * @param offer       滿減
     * @param currency    幣種
     * @return
     */
    void startWithDisountAndPromotion(Date startDate, Date endDate, double spend, double offer, CURRENCY currency);


    /**
     * 掃描產品并添加至Pos機
     *
     * @param product
     * @return
     */
    boolean scanProduct(Product product);

    /**
     * 結算
     *
     * @return
     */
    boolean settleProduct();

    /**
     * 打印小標
     *
     * @return
     */
    void print();

}
