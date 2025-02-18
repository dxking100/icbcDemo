package com.test.icbcdemo.core.calculator;


import com.test.icbcdemo.core.Product;
import com.test.icbcdemo.core.ProductWithQuantity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 計算賬單核心方法
 */
public class BillUtils {
    /**
     * 計算賬單
     * @param tmpProducts 產品信息
     * @param isDisount 是否開啟disount
     * @param isPromotion 是否開啟促銷
     * @param spend 促銷滿
     * @param offer 促銷減
     * @return
     */
    public static Bill calculator(List<Product> tmpProducts, boolean isDisount, Date startTime,Date endTime, boolean isPromotion, double spend, double offer){
        if(tmpProducts == null || tmpProducts.isEmpty()){
            throw  new RuntimeException("計算異常：產品信息不能為空");
        }

        if(isPromotion && (spend <=0 || offer<=0)){
            throw  new RuntimeException("計算異常：促銷開啟時，促銷滿/減 的值必須大于0");
        }

        if(isPromotion && (spend<offer)){
            throw  new RuntimeException("計算異常：促銷開啟時，促銷滿須大于促銷減的值");
        }


        if(isDisount && (startTime == null || endTime==null)) {
            throw new RuntimeException("計算異常：開啟打折模式后，打折開始時間與結束時間為必填");
        }

        if(isDisount && (startTime.after(endTime))) {
            throw new RuntimeException("計算異常：開啟打折模式后，打折開始時間需小于打折結束時間");
        }

        //計算價格
        double orgPrice=0; //原價
        double promotionPrice=0; //促銷價

        List<BillDetail> billDetailList = new ArrayList<>();
        for (Product tmpProduct : tmpProducts) {
            String strTitle= "";
            String strSpend= "";
            String strDiscountPrice = "";
            double tmpOrgPrice = 0;

            if(tmpProduct instanceof ProductWithQuantity){ //計算以過磅的物品
                ProductWithQuantity productWithQuantity = (ProductWithQuantity) tmpProduct;
                //計算產品價格:產品價格*(購買數量*購買單位)/產品單位
                tmpOrgPrice = productWithQuantity.getPrice()*((productWithQuantity.getQuantity()*productWithQuantity.getQuantityPriceUnit().getUnit())/productWithQuantity.getPriceUnit().getUnit());

                if(isDisount && tmpProduct.getDiscount()<1 && new Date().before(endTime)){//如果在某時間內打折
                    //計算產品價格(折扣價):產品價格*折扣
                    tmpOrgPrice = tmpOrgPrice*tmpProduct.getDiscount();
                    strDiscountPrice = " * "+tmpProduct.getDiscount()+"折";
                }

                strTitle = productWithQuantity.getName()+"_"+productWithQuantity.getPrice()+"/"+productWithQuantity.getPriceUnit();
                strSpend = productWithQuantity.getQuantity() +" * " + productWithQuantity.getQuantityPriceUnit()+strDiscountPrice;


            }else if(tmpProduct instanceof Product) { ////計算以件數為單位的物品
                Product product = (Product) tmpProduct;
                //計算產品價格:產品價格*購買數量
                tmpOrgPrice = product.getPrice()*1;

                if(isDisount && tmpProduct.getDiscount()<1  && new Date().before(endTime)){//如果打折
                    //計算產品價格(折扣價):產品價格*折扣
                    tmpOrgPrice = tmpOrgPrice*tmpProduct.getDiscount();
                    strDiscountPrice = " * "+tmpProduct.getDiscount()+"折";
                }

                strTitle = product.getName();
                strSpend = "1 * 個 "+strDiscountPrice;
            }

            //累加金額
            orgPrice+=tmpOrgPrice;

            //添加至票機賬單
            BillDetail billDetail = new BillDetail(tmpProduct.getBarCode(),strTitle,strSpend,tmpOrgPrice);
            billDetailList.add(billDetail);
        }

        Bill bill = new Bill(billDetailList,orgPrice);
        if(isPromotion){ //如果促消價
            //計算促銷價
            double tmpPrePromotionPrice=((int)(orgPrice/spend)*offer);
            promotionPrice = orgPrice-tmpPrePromotionPrice;
            bill.setPromotionPrice(promotionPrice);
        }
        return bill;
    }
}
