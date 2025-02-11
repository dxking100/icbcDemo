package com.test.icbcdemo.shoppingcart;

import com.test.icbcdemo.core.Product;
import com.test.icbcdemo.core.ProductWithQuantity;

import java.util.ArrayList;
import java.util.List;

/**
 * 購物車
 * 用于存放打包后的物品
 */
public class ShoppingCart {
    private List<Product> productList = new ArrayList<>();

    /**
     * 把產品添加至購物車
     * @param product
     * @return
     */
    public boolean addProduct(Product product) {
        if(product == null){
            throw  new RuntimeException("方置購物車中的產品不能為空！");
        }

        //如果產品是需要過磅的
        if(product instanceof ProductWithQuantity){
            ProductWithQuantity productWithQuantity = (ProductWithQuantity) product;
            //如果過磅價格或過磅單位為空時，提示異常
            if(productWithQuantity.getQuantity() <=0 || productWithQuantity.getQuantityPriceUnit() == null){
                throw  new RuntimeException(product.getName()+"，需要過磅！");
            }
        }
        return productList.add(product);
    }

    /**
     * 把產品從購物車中移除
     * @param product
     * @return
     */
    public boolean removeProduct(Product product) {
        return productList.remove(product);
    }

    public List<Product> getProductList() {
        return productList;
    }
}
