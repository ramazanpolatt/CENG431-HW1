package com.company;


import com.company.Application.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String basketPath="./src/baskets.csv";
        String productsPath="./src/products.csv";

        BasketApp basketApp = new BasketApp(productsPath,basketPath);
        basketApp.getRevenueForGivenProduct("COLI-1");
        basketApp.getTopSellingProductByQuantity();
        basketApp.findMostPaidBasket();
        basketApp.getBasketHighestNumberOfDifferentProduct();
        basketApp.findTheMonthWithHighestPriceForAProduct("ELPC-1");
        basketApp.findIncreaseRateOfAProduct("July","ELHE-2");






    }
}
