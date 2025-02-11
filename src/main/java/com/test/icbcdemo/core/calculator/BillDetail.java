package com.test.icbcdemo.core.calculator;

/**
 * 賬單明細信息
 */
public class BillDetail {
    //條型碼
    private String barCode;
    //產品信息
    private String title;
    //購買明細
    private String spend;
    //總價
    private double amount;

    public BillDetail(String barCode, String title, String spend, double amount) {
        this.barCode = barCode;
        this.title = title;
        this.spend = spend;
        this.amount = amount;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getTitle() {
        return title;
    }

    public String getSpend() {
        return spend;
    }

    public double getAmount() {
        return amount;
    }
}
