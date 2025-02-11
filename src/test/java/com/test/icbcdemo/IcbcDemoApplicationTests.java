package com.test.icbcdemo;

import com.test.icbcdemo.core.Product;
import com.test.icbcdemo.core.enums.CURRENCY;
import com.test.icbcdemo.product.Iphone;
import com.test.icbcdemo.product.Mongo;
import com.test.icbcdemo.product.Strawberry;
import com.test.icbcdemo.core.enums.PRICE_UNIT;
import com.test.icbcdemo.product.Apple;
import com.test.icbcdemo.pos.Machine;
import com.test.icbcdemo.pos.impl.POSMachine;
import com.test.icbcdemo.shoppingcart.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class IcbcDemoApplicationTests {

    Machine machine = new POSMachine();

    /**
     * 題目1
     */
    @Test
    void case1() throws CloneNotSupportedException {
        //0.開機
        System.out.println("-----開機");
        machine.start( CURRENCY.MOP);

        //1.設定產品單價
        Apple apple = new Apple("紅富士蘋果","",1,8, PRICE_UNIT.KG,"1123444535356");
        Strawberry strawberry = new Strawberry("日本大草莓","",0.5,13, PRICE_UNIT.KG,"1123442225356");

        //2.A用戶購買若干斤蘋果與草莓
        System.out.println("-----過磅");
        Product pack1 = apple.pack(600, PRICE_UNIT.G);//購買蘋果600克
        Product pack2 = strawberry.pack(10, PRICE_UNIT.KG);//購買10公斤草莓

        //3.添加至購物車
        ShoppingCart cart1 = new ShoppingCart();
        cart1.addProduct(pack1);
        cart1.addProduct(pack2);

        //4.推至柜臺結賬
        System.out.println("-----收銀臺掃描");
        for (Product product : cart1.getProductList()) {
            machine.scanProduct(product);
        }
        //4.1 取消其中一包要購買的產品
        // machine.removeProduct(pack3);

        //4.2結算
        System.out.println("-----結賬");
        machine.settleProduct();

        //4.3打印小票
        System.out.println("-----打印小票");
        machine.print();

    }


    /**
     * 題目2
     */
    @Test
    void case2() throws CloneNotSupportedException {
        //0.開機
        System.out.println("-----開機");
        machine.start( CURRENCY.MOP);


        System.out.println("-----上架商品");
        //1.設定產品單價
        Apple apple = new Apple("紅富士蘋果","",1,8, PRICE_UNIT.KG,"1123444535356");
        Strawberry strawberry = new Strawberry("日本大草莓","",0.5,25, PRICE_UNIT.KG,"1123442225356");
        Mongo mongo = new Mongo("菲xx大芒果","",0.5,20, PRICE_UNIT.KG,"112344222535644");

        //2.A用戶購買若干斤蘋果與草莓
        System.out.println("-----過磅");
        Product pack1 = apple.pack(600, PRICE_UNIT.G);//購買蘋果600克
        Product pack2 = strawberry.pack(10, PRICE_UNIT.KG);//購買10公斤草莓
        Product pack3 = mongo.pack(2000, PRICE_UNIT.G);//購買2000克芒果


        //3.添加至購物車
        ShoppingCart cart1 = new ShoppingCart();
        cart1.addProduct(pack1);
        cart1.addProduct(pack2);
        cart1.addProduct(pack3);

        //4.推至柜臺結賬
        System.out.println("-----收銀臺掃描");
        for (Product product : cart1.getProductList()) {
            machine.scanProduct(product);
        }
        //4.1 取消其中一包要購買的產品
        // machine.removeProduct(pack3);

        //4.2結算
        System.out.println("-----結賬");
        machine.settleProduct();

        //4.3打印小票
        System.out.println("-----打印小票");
        machine.print();

    }

    /**
     * 題目3
     */
    @Test
    void case3() throws CloneNotSupportedException, ParseException {
        //0.開機
        System.out.println("-----開機");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        machine.startWithDisount(format.parse("20250211121111"),format.parse("20250211163000") , CURRENCY.MOP);

        System.out.println("-----上架商品");
        //1.設定產品單價
        Apple apple = new Apple("紅富士蘋果","",1,8, PRICE_UNIT.KG,"1123444535356");
        Strawberry strawberry = new Strawberry("日本大草莓","",0.80,25, PRICE_UNIT.KG,"1123442225356");
        Mongo mongo = new Mongo("菲xx大芒果","",1,20, PRICE_UNIT.KG,"112344222535644");

        //2.A用戶購買若干斤蘋果與草莓
        System.out.println("-----過磅");
        Product pack1 = apple.pack(600, PRICE_UNIT.G);//購買蘋果600克
        Product pack2 = strawberry.pack(10, PRICE_UNIT.KG);//購買10公斤草莓
        Product pack3 = mongo.pack(2000, PRICE_UNIT.G);//購買2000克芒果


        //3.添加至購物車
        ShoppingCart cart1 = new ShoppingCart();
        cart1.addProduct(pack1);
        cart1.addProduct(pack2);
        cart1.addProduct(pack3);

        //4.推至柜臺結賬
        System.out.println("-----收銀臺掃描");
        for (Product product : cart1.getProductList()) {
            machine.scanProduct(product);
        }
        //4.1 取消其中一包要購買的產品
        // machine.removeProduct(pack3);

        //4.2結算
        System.out.println("-----結賬");
        machine.settleProduct();

        //4.3打印小票
        System.out.println("-----打印小票");
        machine.print();
    }

    /**
     * 題目4
     */
    @Test
    void case4() throws CloneNotSupportedException, ParseException {
        //0.開機
        System.out.println("-----開機");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        machine.startWithPromotion(100,10, CURRENCY.MOP);


        System.out.println("-----上架商品");
        //1.設定產品單價
        Apple apple = new Apple("紅富士蘋果","",1,8, PRICE_UNIT.KG,"1123444535356");
        Strawberry strawberry = new Strawberry("日本大草莓","",0.80,25, PRICE_UNIT.KG,"1123442225356");
        Mongo mongo = new Mongo("菲xx大芒果","",1,20, PRICE_UNIT.KG,"112344222535644");

        //2.A用戶購買若干斤蘋果與草莓
        System.out.println("-----過磅");
        Product pack1 = apple.pack(600, PRICE_UNIT.G);//購買蘋果600克
        Product pack2 = strawberry.pack(10, PRICE_UNIT.KG);//購買10公斤草莓
        Product pack3 = mongo.pack(2000, PRICE_UNIT.G);//購買2000克芒果


        //3.添加至購物車
        ShoppingCart cart1 = new ShoppingCart();
        cart1.addProduct(pack1);
        cart1.addProduct(pack2);
        cart1.addProduct(pack3);

        //4.推至柜臺結賬
        System.out.println("-----收銀臺掃描");
        for (Product product : cart1.getProductList()) {
            machine.scanProduct(product);
        }
        //4.1 取消其中一包要購買的產品
        // machine.removeProduct(pack3);

        //4.2結算
        System.out.println("-----結賬");
        machine.settleProduct();

        //4.3打印小票
        System.out.println("-----打印小票");
        machine.print();
    }

    /**
     * 題目5:添加不用過磅的產品，如iphone
     */
    @Test
    void case5() throws CloneNotSupportedException, ParseException {
        //0.開機
        System.out.println("-----開機");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        machine.startWithDisountAndPromotion(format.parse("20250211121111"),format.parse("20250211163000"),100,10, CURRENCY.MOP);


        System.out.println("-----上架商品");
        //1.設定產品單價
        Apple apple = new Apple("紅富士蘋果","",1,8, PRICE_UNIT.KG,"1123444535356");
        Strawberry strawberry = new Strawberry("日本大草莓","",0.80,25, PRICE_UNIT.KG,"1123442225356");
        Mongo mongo = new Mongo("菲xx大芒果","",1,20, PRICE_UNIT.KG,"112344222535644");
        Iphone iphone = new Iphone("iphone 16 pro max","",1,10000, "112344222533335644");

        //2.A用戶購買若干斤蘋果與草莓
        System.out.println("-----過磅");
        Product pack1 = apple.pack(600, PRICE_UNIT.G);//購買蘋果600克
        Product pack2 = strawberry.pack(10, PRICE_UNIT.KG);//購買10公斤草莓
        Product pack3 = mongo.pack(2000, PRICE_UNIT.G);//購買2000克芒果


        //3.添加至購物車
        ShoppingCart cart1 = new ShoppingCart();
        cart1.addProduct(pack1);
        cart1.addProduct(pack2);
        cart1.addProduct(pack3);
        cart1.addProduct(iphone);

        //4.推至柜臺結賬
        System.out.println("-----收銀臺掃描");
        for (Product product : cart1.getProductList()) {
            machine.scanProduct(product);
        }
        //4.1 取消其中一包要購買的產品
        // machine.removeProduct(pack3);

        //4.2結算
        System.out.println("-----結賬");
        machine.settleProduct();

        //4.3打印小票
        System.out.println("-----打印小票");
        machine.print();
    }


}
