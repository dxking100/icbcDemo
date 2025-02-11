package com.test.icbcdemo.product;

import com.test.icbcdemo.core.ProductWithQuantity;
import com.test.icbcdemo.core.enums.PRICE_UNIT;

public class Strawberry extends ProductWithQuantity {

    public Strawberry(String name, String description, double discount, double price, PRICE_UNIT priceUnit,String barCode) {
        super(name, description, discount, price, priceUnit,barCode);
    }
}
