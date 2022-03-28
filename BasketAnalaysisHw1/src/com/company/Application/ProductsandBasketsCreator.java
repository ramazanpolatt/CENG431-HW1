package com.company.Application;

import com.company.Clothing.Coat;
import com.company.Clothing.Skirt;
import com.company.Cosmetic.Lipstick;
import com.company.Cosmetic.Perfume;
import com.company.Electronic.Headphone;
import com.company.Electronic.Monitor;
import com.company.Electronic.PersonalComputer;
import com.company.Electronic.Phone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
This class responsibilies are creating product and basket objects and returning them via functions
 */
public class ProductsandBasketsCreator implements IProductandBaskets {


    private String productsPath;
    private String basketsPath;
    private FileOperations fileOperator;
    private Collection<IProduct> products;
    private Collection<IBasket> baskets;
    private final String[] monthStringArray = {"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};


    public ProductsandBasketsCreator(String productsPath, String basketsPath) throws IOException {


        this.productsPath=productsPath;
        this.basketsPath=basketsPath;
        fileOperator = new FileOperations(productsPath,basketsPath);
        createProducts();

    }

    @Override
    public Collection<IProduct> getProducts() throws IOException {

      return products;
    }

    private void createProducts()  throws IOException
    {
        Collection productLines = fileOperator.getProductLines();
        Iterator<String> iter = productLines.iterator();
        String line="";
        String identifier;
        String [] splittedLine;
        products = new ArrayList<>();

        while(iter.hasNext())
        {
            line=iter.next();
            splittedLine=line.split(";");
            identifier=splittedLine[0].substring(0,2);
            switch (identifier)
            {
                case "EL" :
                    products.add(electronicProductCreator(splittedLine));
                    break;
                case "CL" :
                    products.add(clothProductCreator(splittedLine));
                    break;
                case "CO":
                    products.add(cosmeticProductCreator(splittedLine));
                    break;
                default:

                    System.out.println("Read "+productsPath+" file contains not existing categories.");
                    System.exit(0);


            }

        }




    }

    private IProduct cosmeticProductCreator(String[] splittedLine) {
        String subCategory=splittedLine[0].substring(2,4);
        String id=splittedLine[0];

        IProduct tempCosmeticProduct = null;
        switch (subCategory)
        {
            case "PE":
                tempCosmeticProduct = new Perfume(id,splittedLine[1],Integer.parseInt(splittedLine[2]),splittedLine[3]);
                break;



            case "LI":
                tempCosmeticProduct = new Lipstick(id,splittedLine[1],Integer.parseInt(splittedLine[2]),splittedLine[3],splittedLine[4]);
                break;



            default:
                System.out.println("The Cosmetic product read on the products csv does not exist in the existing specific types ");
                System.exit(0);



        }


        return  tempCosmeticProduct;

    }

    private IProduct clothProductCreator(String[] splittedLine) {
        String subCategory=splittedLine[0].substring(2,4);
        String id=splittedLine[0];

        IProduct tempClothProduct = null;
        switch (subCategory)
        {
            case "CO":
                tempClothProduct = new Coat(id,splittedLine[1],splittedLine[2],splittedLine[3],splittedLine[4]);



                break;



            case "SK":
                tempClothProduct = new Skirt(id,splittedLine[1],splittedLine[2],splittedLine[3],splittedLine[4]);
                break;



            default:
                System.out.println("The Clothing product read on the "+productsPath+" file  does not exist in the existing specific types ");
                System.exit(0);



        }


        return  tempClothProduct;

    }

    private IProduct electronicProductCreator(String[] splittedLine)
    {

        String subCategory=splittedLine[0].substring(2,4);
        String id=splittedLine[0];

        IProduct tempElectronicProduct = null;
        switch (subCategory)
        {
            case "PC":
                tempElectronicProduct = new PersonalComputer(id,splittedLine[1],
                        Short.parseShort(splittedLine[2]),Short.parseShort(splittedLine[3]),splittedLine[4]);
                break;



            case "MO":
                tempElectronicProduct = new Monitor(id,splittedLine[1],Float.parseFloat(splittedLine[2].replace(',','.')));
                break;


            case "PH":

                tempElectronicProduct = new Phone(id,splittedLine[1],Short.parseShort(splittedLine[2]));
                break;
            case "HE":
                tempElectronicProduct = new Headphone(id,splittedLine[1],Float.parseFloat(splittedLine[2]));
                break;

            default:
                System.out.println("The Electronic product read on the " + productsPath+" file  does not exist in the existing specific types ");
                System.exit(0);

                

        }


        return  tempElectronicProduct;

    }


    @Override
    public Collection<IBasket> getBaskets() throws IOException {

        Collection<String> productLines = fileOperator.getBasketLines();
        Iterator<String> iter = productLines.iterator();
        baskets = new ArrayList<>();
        int basketId =1,basketProductCount=0,price=0,quantity=0;//initializing values
        String line="";
        String [] splittedLine;
        String date;

        String productId;
        while(iter.hasNext()) {
            line = iter.next();
            splittedLine = line.split(";");
            basketId=Integer.parseInt(splittedLine[0]);
            date=splittedLine[splittedLine.length-1];

            try{

                int monthNumber=Integer.parseInt(date.split("\\.")[1]);

                Months tempMonth= Months.valueOf(monthStringArray[monthNumber-1]);
                IBasket tempBasket = new Basket(basketId,date,tempMonth);
                basketProductCount=(splittedLine.length-2)/3;
                for (int index = 0; index <basketProductCount; index++) {
                    productId=splittedLine[3*index+1];
                    price=Integer.parseInt(splittedLine[3*index+2]);
                    quantity= Integer.parseInt(splittedLine[3*index+3]);
                    int[] tempIntegerArray = {price,quantity};
                    tempBasket.addProduct(getProductWithId(productId),
                            tempIntegerArray);

                }
                baskets.add(tempBasket);

            }
            catch(EnumConstantNotPresentException e)
            {
                System.out.println("Some error occurred while reading month value from basket records");
                System.exit(0);
            }
            catch(IndexOutOfBoundsException e)
            {
                System.out.println("Error occurred while creating basket object specifically(Index out of bounds)");
                System.exit(0);
            }



        }
        return baskets;
    }



    private IProduct getProductWithId(String id)
    {
       Iterator<IProduct> iter = products.iterator();
       Boolean found=false;
       IProduct foundProduct=null;
       while(iter.hasNext()&&!found)
       {
           if((foundProduct=iter.next()).getID().equals(id))
           {

            found = true;
           }


       }

       if(!found)
       {
           System.out.println("The basket record contains non existing product");
           System.exit(0);
       }
       return foundProduct;
    }






}



