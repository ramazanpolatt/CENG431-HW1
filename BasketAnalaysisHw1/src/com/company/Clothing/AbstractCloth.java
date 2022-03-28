package com.company.Clothing;

import com.company.Application.IProduct;

import java.util.Objects;

public abstract class AbstractCloth implements IProduct {

    private String id;
    private String brand;
    private String size;
    private String color;
    public AbstractCloth(String id ,String brand, String size,String color) {
        this.id= id;
        this.brand = brand;
        this.size = size;
        this.color=color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCloth that = (AbstractCloth) o;
        return id.equals(that.id) && brand.equals(that.brand) && size.equals(that.size);
    }

    @Override
    public String toString() {
        return  id + ' ' +
                 brand + ' ' +
                ' ' +
                 color;
    }
}
