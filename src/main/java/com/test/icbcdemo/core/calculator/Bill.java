package com.test.icbcdemo.core.calculator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 賬單信息
 */
public class Bill {
    private List<BillDetail> billDetailList = new ArrayList<BillDetail>();
    //總價
    private double orgPrice;
    //促銷價
    private double promotionPrice;
    //賬單日期
    private Date date = new Date();

    public Bill(List<BillDetail> billDetailList, double orgPrice) {
        this.billDetailList = billDetailList;
        this.orgPrice = orgPrice;
    }

    public List<BillDetail> getBillDetailList() {
        return billDetailList;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public double getOrgPrice() {
        return orgPrice;
    }

    public Date getDate() {
        return date;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }


}
