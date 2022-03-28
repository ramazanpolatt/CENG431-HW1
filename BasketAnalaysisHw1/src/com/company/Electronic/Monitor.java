package com.company.Electronic;

import java.util.Objects;

public class Monitor extends AbstractElectronic{

    private float screenSize;

    public Monitor(String id, String title,float screenSize) {
        super(id, title);
        this.screenSize=screenSize;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitor monitor = (Monitor) o;
        return Float.compare(monitor.screenSize, screenSize) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(screenSize);
    }

    @Override
    public String toString() {
        return  super.toString() +
                " " + screenSize ;
    }
}
