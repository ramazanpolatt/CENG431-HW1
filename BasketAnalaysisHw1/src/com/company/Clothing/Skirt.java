package com.company.Clothing;

import java.awt.image.TileObserver;

public class Skirt  extends AbstractCloth {
    private String hemline;

    public Skirt(String id, String brand, String size,String color, String hemline) {
        super(id, brand, size,color);
        this.hemline = hemline;
    }


    public String getHemline() {
        return hemline;
    }

    public void setHemline(String hemline) {
        this.hemline = hemline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Skirt skirt = (Skirt) o;
        return hemline.equals(skirt.hemline);
    }

    @Override
    public String toString() {
        return  super.toString() +
                " " + hemline + ' ' ;
    }
}
