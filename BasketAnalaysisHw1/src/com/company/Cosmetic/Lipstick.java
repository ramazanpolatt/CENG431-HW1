package com.company.Cosmetic;

import java.util.Objects;

public class Lipstick extends AbstractCosmetic{

    private String lipstickType;
    private String color;

    public Lipstick(String id, String brand, int milliliter, String color, String lipstickType) {
        super(id, brand, milliliter);
        this.color=color;
        this.lipstickType = lipstickType;
    }

    public String getLipstickType() {
        return lipstickType;
    }

    public void setLipstickType(String lipstickType) {
        this.lipstickType = lipstickType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lipstick lipstick = (Lipstick) o;
        return Objects.equals(lipstickType, lipstick.lipstickType) && Objects.equals(color, lipstick.color);
    }

    @Override
    public String toString() {
        return super.toString() +" "+
               lipstickType + ' ' +
                color;
    }
}
