package com.company.Application;

import java.util.Map;

public interface IBasket {


    public int getBasketID();
    public String  getDate();
    public boolean hasProduct(IProduct product);
    public int getQuantity(IProduct product);
    public int getPrice(IProduct product);
    public void addProduct(IProduct product,int[] priceAndQuantity);
    public Months getMonth();
    public Map<IProduct, int[]> getProductRecord();

}
