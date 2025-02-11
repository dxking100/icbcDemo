package com.test.icbcdemo.product;

import com.test.icbcdemo.core.Product;
import com.test.icbcdemo.core.ProductWithQuantity;
import com.test.icbcdemo.core.enums.PRICE_UNIT;

public class Iphone extends Product {

    public Iphone(String name, String description, double discount, double price, String barCode) {
        super(name, description, discount, price,barCode);
    }
}
