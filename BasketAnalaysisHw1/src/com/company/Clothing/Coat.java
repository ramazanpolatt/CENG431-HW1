package com.company.Clothing;

import java.util.Objects;

public class Coat extends  AbstractCloth{
    private String type;

    public Coat(String id,String brand, String size,String color, String type) {
        super(id, brand, size,color);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Coat coat = (Coat) o;
        return type.equals(coat.type);
    }

    @Override
    public String toString() {

        return super.toString() +" "+ type + ' ';
    }
}
