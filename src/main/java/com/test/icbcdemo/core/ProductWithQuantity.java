package com.test.icbcdemo.core;

import com.test.icbcdemo.core.enums.PRICE_UNIT;

/**
 * 定義產品的抽象類
 */
public abstract class ProductWithQuantity extends Product {
    /**
     * 產品單價-單位
     */
    public PRICE_UNIT priceUnit;


    /**
     * 購買數量
     */
    private double quantity;

    /**
     * 購買數量單位
     */
    private PRICE_UNIT quantityPriceUnit;

    public ProductWithQuantity(String name, String description, double discount, double price, PRICE_UNIT priceUnit,String barCode) {
        super(name,description,discount,price,barCode);
        if(priceUnit == null){
            throw new RuntimeException("初始化產品（帶計算單位）異常");
        }

        this.priceUnit = priceUnit;
    }
    /**
     * 產品打包
     * @param quantity 購買數量
     * @param quantityPriceUnit 購買數量單位
     * @return
     */
    public Product pack(double quantity,PRICE_UNIT quantityPriceUnit) throws CloneNotSupportedException {
        System.out.println("產品" + this.getName() + "過磅");
        this.quantity = quantity;
        this.quantityPriceUnit = quantityPriceUnit;
        return (ProductWithQuantity) this.clone();

    }

    public PRICE_UNIT getQuantityPriceUnit() {
        return quantityPriceUnit;
    }

    public double getQuantity() {
        return quantity;
    }


    public PRICE_UNIT getPriceUnit() {
        return priceUnit;
    }
}
