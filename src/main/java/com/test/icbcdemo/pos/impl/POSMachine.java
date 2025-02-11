package com.test.icbcdemo.pos.impl;

import com.test.icbcdemo.core.calculator.Bill;
import com.test.icbcdemo.core.calculator.BillDetail;
import com.test.icbcdemo.core.calculator.BillUtils;
import com.test.icbcdemo.core.enums.CURRENCY;
import com.test.icbcdemo.pos.Machine;
import com.test.icbcdemo.core.Product;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;


public class POSMachine implements Machine {
    private List<Product> tmpProducts = new ArrayList<>();
    private Bill tmpBill; //單據信息

    /**
     * 是否開啟discount
     */
    private boolean isDisount;

    /**
     * 打拍開始時間
     */
    private Date startTime;

    /**
     * 打折結束時間
     */
    private Date endTime;

    /**
     * 是否支持滿減活動
     */
    private boolean isPromotion;

    /**
     * 滿 金額
     */
    private double spend;

    /**
     * 減 金額
     */
    private double offer;

    private CURRENCY currency;

    private void start(boolean isDisount,Date startTime,Date endTime, boolean isPromotion, double spend, double offer, CURRENCY currency) {
        if(isPromotion && (spend<=0 || offer<=0)) {
            throw new RuntimeException("開機異常：開啟促銷模式后，購滿金額或購減金額需大于0");
        }

        if(isPromotion && (spend<offer)) {
            throw new RuntimeException("開機異常：開啟促銷模式后，購滿金額需大于購減金額");
        }


        if(isDisount && (startTime == null || endTime==null)) {
            throw new RuntimeException("開機異常：開啟打折模式后，打折開始時間與結束時間為必填");
        }

        if(isDisount && (startTime.after(endTime))) {
            throw new RuntimeException("開機異常：開啟打折模式后，打折開始時間需小于打折結束時間");
        }

        this.isDisount = isDisount;
        this.isPromotion = isPromotion;
        this.spend = spend;
        this.offer = offer;
        this.currency = currency;
        this.startTime = startTime;
        this.endTime = endTime;
        System.out.println("開機成功-"+String.format("開啟折扣: %s,開始折扣時間：%s,結束折扣時間：%s, 開啟促銷: %s, 滿: %.2f 減: %.2f,幣種：%s", isDisount,startTime,endTime, isPromotion, spend, offer, currency));

    }

    @Override
    public void start(CURRENCY currency) {
        start(false,null,null,false,0,0,currency);
    }

    @Override
    public void startWithDisount(Date startDate, Date endDate, CURRENCY currency) {
        start(true,startDate,endDate,false,0,0,currency);
    }

    @Override
    public void startWithPromotion( double spend, double offer, CURRENCY currency) {
        start(false,null,null,true,spend,offer,currency);
    }
    @Override
    public void startWithDisountAndPromotion( Date startDate, Date endDate,double spend, double offer, CURRENCY currency) {
        start(true,startDate,endDate,true,spend,offer,currency);
    }

    @Override
    public boolean removeProduct(Product product) {
        System.out.println("pos機中移除產品" + product.getName());
        return tmpProducts.remove(product);
    }

    @Override
    public boolean scanProduct(Product product) {
        System.out.println("pos機中添加產品" + product.getName());
        return tmpProducts.add(product);
    }

    @Override
    public boolean settleProduct() {
        if(tmpProducts.size()<=0){
            System.out.println("pos機中沒有結算的產品!");
        }
        this.tmpBill = BillUtils.calculator(tmpProducts,isDisount,startTime,endTime,isPromotion,spend,offer);
        return false;
    }

    @Override
    public void print() {
        if(this.tmpBill == null){
            throw new RuntimeException("請先執行結算方法");
        }
        System.out.println("----------------打印賬單------------------");
        System.out.println("賬單日期:"+this.tmpBill.getDate());
        for (BillDetail billDetail : this.tmpBill.getBillDetailList()) {
            System.out.println(billDetail.getTitle());
            System.out.println("--"+billDetail.getSpend()+"    "+currency+String.format("%.2f", billDetail.getAmount()));
        }
        System.out.println("-----------------------------------------");
        System.out.println("原價："+currency+String.format("%.2f", this.tmpBill.getOrgPrice()));
        System.out.println("促銷價："+currency+String.format("%.2f", this.tmpBill.getPromotionPrice()));


        //清空緩存信息
        tmpProducts=null;
        tmpBill=null;

    }
}
