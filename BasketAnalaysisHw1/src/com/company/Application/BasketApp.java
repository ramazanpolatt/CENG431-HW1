package com.company.Application;

import java.io.IOException;
import java.util.*;

public class BasketApp {

    Collection<IProduct> products;
    Collection<IBasket> baskets;
    private String productsPath;
    private String basketsPath;

    public BasketApp(String productsPath,String basketsPath)  {

        this.productsPath=productsPath;
        this.basketsPath=basketsPath;
        try
        {
            initializer();

        }
        catch(IOException exception)
        {

            System.out.println("File path is wrong");
            System.exit(0);
        }

    }

    private void initializer() throws IOException{

        IProductandBaskets productAndBaskets = new ProductsandBasketsCreator(productsPath,basketsPath);
        products=productAndBaskets.getProducts();
        baskets=productAndBaskets.getBaskets();


    }

    /* This function calculate total revenue for COLI-1
     *  Uses helper method,which getProduct(), to take COLI-1 object
     *  Baskets are stored in a map<IProduct,[quantity,price]> so we can reach quantity and price for specific Object
     * */
    public void getRevenueForGivenProduct(String productName) {
        if(!isGivenProductAcceptable(productName ))
        {
            System.out.println("The given product id is invalid");
        }
        else
        {
            int totalRevenue = 0;
            int tempPrice = 0;
            int tempQuantity = 0;
            IProduct tempProduct = getProductByString(productName);
            IBasket tempBasket = null;
            Iterator<IBasket> basketIterator = baskets.iterator();
            while (basketIterator.hasNext()) {
                tempBasket = basketIterator.next();
                if(tempBasket.hasProduct(tempProduct))
                {
                    tempPrice = tempBasket.getPrice(tempProduct);
                    tempQuantity = tempBasket.getQuantity(tempProduct);
                    totalRevenue += tempPrice * tempQuantity;
                }


            }
            System.out.printf("Total revenue for %s product is  %d\n",productName,totalRevenue);
        }


    }

    //This function take productID as a String type and returns Product object with givenID
    private IProduct getProductByString(String productID) {
        boolean isFound = false;
        IProduct tempProduct = null;
        Iterator<IProduct> tempIter = products.iterator();
        while (!isFound) {
            tempProduct = tempIter.next();
            if (tempProduct.getID().equals(productID)) {
                isFound = true;
            }
        }

        return tempProduct;
    }


    public void getTopSellingProductByQuantity() {
        int highestQuantity = 0;
        Iterator<IProduct> productIterator = products.iterator();//iterator for product list
        IProduct tempProduct = null;
        IBasket tempBasket = null;
        Map<IProduct, Integer> productMap = new HashMap<>();//this map holds products with the highest quantity
        while (productIterator.hasNext()) {//it traverses for all products in basket
            int currentQuantity = 0;
            Iterator<IBasket> basketIterator = baskets.iterator();//Each time we should reset iterator because if we don't do that it traverse for first product than return null for others
            tempProduct = productIterator.next();//Searched product in baskets
            while (basketIterator.hasNext()) {
                tempBasket = basketIterator.next();
                if (tempBasket.hasProduct(tempProduct))//checks given product is inside basket or not
                {
                    currentQuantity += tempBasket.getQuantity(tempProduct);//if it is, increment quantity for that product
                }
            }
            //System.out.println("Current product is:"+tempProduct.getID()+"quantity is :"+currentQuantity);
            if (currentQuantity >= highestQuantity) {
                if(currentQuantity>highestQuantity){//if current quantity is greater than the highest quantity clear the Map
                    highestQuantity = currentQuantity;//set highest as a current
                    productMap.clear();
                }
                productMap.put(tempProduct, currentQuantity);
                //System.out.println("New highest is: "+tempProduct.getID()+" Quantity is "+currentQuantity);
            }
        }
        System.out.print("Top selling product by quantity :     ");
        for (var entry : productMap.entrySet()){
            System.out.println(entry.getKey().toString()+" Value is :"+entry.getValue());
        }
    }

    //This function prints given entry
    private void printGivenEntry(Map.Entry entry){
        System.out.println(entry.getKey().toString()+": "+entry.getValue());
    }


    public void findMostPaidBasket(){
        Iterator<IBasket> basketIterator = baskets.iterator();
        Map<IBasket,Integer> basketWithCost=new HashMap<>();
        int highestPaid=0;
        while(basketIterator.hasNext()){
            int currentPaid=0;
            IBasket tempBasket = basketIterator.next();
            Map<IProduct, int[]> tempMap = tempBasket.getProductRecord();//gets Products and its quantity and prices
            for (var entry : tempMap.entrySet()) {
                currentPaid+=entry.getValue()[0]*entry.getValue()[1];//calculate total cost of that basket
            }
            if (currentPaid>=highestPaid){
                if(currentPaid>highestPaid){//If current cost is higher, clear the Map and add current basket. So highest will be only element in the Map(If equal program will add it to Map)
                    highestPaid=currentPaid;
                    basketWithCost.clear();
                }
                basketWithCost.put(tempBasket,currentPaid);

            }//end of outer if

        }//end of while

        for (var entry : basketWithCost.entrySet()){//Printing highest paid baskets
            System.out.println("The most paid basket is Basket "+entry.getKey().getBasketID()+" and total payment is  "+entry.getValue());
        }
    }



    public void getBasketHighestNumberOfDifferentProduct(){
        Iterator<IBasket> basketIterator = baskets.iterator();
        IBasket tempBasket=null;
        Map<IProduct,int[]> productRecords;
        Map<IBasket,Integer> basketAndQuantities = new HashMap<>();
        int highestQuantity = 0;
        int currentQuantity = 0;
        while (basketIterator.hasNext()) {
            tempBasket=basketIterator.next();
            productRecords = tempBasket.getProductRecord();
            currentQuantity=productRecords.size();
            if (currentQuantity>=highestQuantity){
                if (currentQuantity>highestQuantity){
                    basketAndQuantities.clear();
                    highestQuantity=currentQuantity;
                }

                    basketAndQuantities.put(tempBasket,currentQuantity);

            }//end of outer else

        }//end of while
        for (var entry : basketAndQuantities.entrySet()){
            System.out.println("Basket with the largest number of different products is  : "+entry.getKey().getBasketID()+"   Different product number is :"+entry.getValue());
        }
    }//end of function


    public void findTheMonthWithHighestPriceForAProduct(String productId){
        if(!isGivenProductAcceptable(productId))
        {

            System.out.println("The given product id is invalid");
        }
        else
        {


            IProduct productWithGivenID = getProductByString(productId);
            Iterator<IBasket> basketIterator = baskets.iterator();
            List<Months> highestMonths = new ArrayList<>();
            IBasket tempBasket=null;
            int currentPrice=0;
            int highestPrice=0;

            while(basketIterator.hasNext()){

                tempBasket=basketIterator.next();

                if (tempBasket.hasProduct(productWithGivenID))
                {
                    currentPrice=tempBasket.getPrice(productWithGivenID);

                    if (currentPrice>=highestPrice){

                        if (currentPrice>highestPrice)
                        {
                            highestMonths.clear();
                            highestPrice=currentPrice;
                        }

                        Months currentMonth=tempBasket.getMonth();
                        highestMonths.add(currentMonth);

                    }

                }//end of first if
            }//end of while
            System.out.print ("The month in which the "+ productId+" as sold the most expensive : ");
            for (int index = 0; index < highestMonths.size(); index++) {
                System.out.print(highestMonths.get(index)+" price is: "+highestPrice+"  ");
            }    }
          System.out.println("");


    }
    public void findIncreaseRateOfAProduct(String month, String productID){
        month=month.toUpperCase();
        if(!isGivenMonthAcceptable(month)||!isGivenProductAcceptable(productID))
        {
            System.out.println("Given month is  invalid month or the given product id  is invalid");
        }
        else
        {
            IProduct productWithGivenID = getProductByString(productID);
            IBasket tempBasket = null;
            List<Integer> pricesOfProduct = new ArrayList<>();
            Iterator<IBasket> basketIterator = baskets.iterator();
            while (basketIterator.hasNext()){
                tempBasket=basketIterator.next();
                Months currentMonth = Months.valueOf(month.toUpperCase());
                if (!currentMonth.equals(tempBasket.getMonth())){
                    continue;
                }
                else {
                    if (tempBasket.hasProduct(productWithGivenID)){
                        pricesOfProduct.add(tempBasket.getPrice(productWithGivenID));
                    }
                }

            }
            Collections.sort(pricesOfProduct);

           switch (pricesOfProduct.size())
           {

               case 0:
                   System.out.println("The given product is not found at the desired date.");
                   break;
               case 1 :
                   System.out.println("There is no increase rate for a single product and the price is" + pricesOfProduct.get(0));
                   break;
               default:
                   int increaseRate=((pricesOfProduct.get(pricesOfProduct.size()-1)-pricesOfProduct.get(0))*100)/pricesOfProduct.get(0);
                   System.out.println("Price increase rate of "+productID+" in "+month+ " is %"+increaseRate);


           }
        }
    }

    private boolean isGivenMonthAcceptable(String monthString)
    {

        for (Months month:Months.values()
             ) {
            if(month.name().equals(monthString))
            {
                return true;
            }

        }
        return false;
    }

    private boolean isGivenProductAcceptable(String productString)
    {
        for (IProduct product:products
             ) {
            if(product.getID().equals(productString))
            {
                return true;
            }

        }
        return false;

    }

}
