package com.company.Cosmetic;

import java.util.Objects;

public class Perfume extends AbstractCosmetic{

    private String fragranceType;

    public Perfume(String id, String brand, int milliliter,  String fragranceType) {
        super(id, brand, milliliter);
        this.fragranceType = fragranceType;
    }

    public String getFragranceType() {
        return fragranceType;
    }

    public void setFragranceType(String fragranceType) {
        this.fragranceType = fragranceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Perfume perfume = (Perfume) o;
        return fragranceType.equals(perfume.fragranceType);
    }

    @Override
    public String toString() {
        return  super.toString() +
                fragranceType;
    }
}
