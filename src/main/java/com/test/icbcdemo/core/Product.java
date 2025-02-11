package com.test.icbcdemo.core;

/**
 * 定義產品的抽象類
 */
public abstract class Product  implements Cloneable {
    //產品名稱
    private String name;
    //產品描述
    private String description;
    //折扣
    private double discount;
    //單價
    private double price;

    //條型碼
    private String barCode;

    public Product(String name, String description, double discount, double price,String barCode) {
        if(name == null ||  price < 0 || barCode == null){
            throw new RuntimeException("初始化產品異常");
        }

        if(discount>1 || discount<=0){
            throw new RuntimeException("初始化產品折扣異常");
        }

        if(price<=0){
            throw new RuntimeException("初始化產品產品價格不能小于等于0");
        }


        this.name = name;
        this.description = description;
        this.discount = discount;
        this.price = price;
        this.barCode = barCode;
    }


    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }


    public String getBarCode() {
        return barCode;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
