package com.company.Application;

import java.util.HashMap;
import java.util.Map;

public class Basket implements IBasket {

    private int id;
    private String date;
    private Months month;
    private Map<IProduct,int[]> productRecord;


    public Map<IProduct, int[]> getProductRecord() {
        return productRecord;
    }

    public Basket(){
        new Basket(0,"",Months.JANUARY);
    }

    public Basket(int id, String date,Months month) {
        this.id = id;
        this.date = date;
        this.productRecord = new HashMap<>();
        this.month=month;

    }


    @Override
    public int getBasketID() {
        return id;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public boolean hasProduct(IProduct product) {
        return productRecord.containsKey(product);
    }


    @Override
    public int getPrice(IProduct product) {
        int[] tempInt=productRecord.get(product);
        return tempInt[0];
    }

    @Override
    public void addProduct(IProduct product,int[] priceAndQuantity) {
        productRecord.put(product,priceAndQuantity);

    }

    @Override
    public Months getMonth() {
        return month;
    }


    @Override
    public int getQuantity(IProduct product) {
        int[] tempInt= productRecord.get(product);
        return tempInt[1];
    }


}
