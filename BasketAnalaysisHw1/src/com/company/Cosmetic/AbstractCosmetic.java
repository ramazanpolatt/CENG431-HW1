package com.company.Cosmetic;

import com.company.Application.IProduct;

import java.util.Objects;

public abstract class AbstractCosmetic implements IProduct {

    private String id;
    private String brand;
    private int milliliter;



    public AbstractCosmetic(String id, String brand, int milliliter) {
        this.id = id;
        this.brand = brand;
        this.milliliter = milliliter;

    }

    @Override
    public String getID() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMilliliter() {
        return milliliter;
    }

    public void setMilliliter(int milliliter) {
        this.milliliter = milliliter;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCosmetic that = (AbstractCosmetic) o;
        return milliliter == that.milliliter && id.equals(that.id) && brand.equals(that.brand);
    }

    @Override
    public String toString() {
        return  id + ' ' +
                brand + ' '
                + milliliter;
    }
}
