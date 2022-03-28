package com.company.Electronic;

import java.util.Objects;

public class Headphone extends AbstractElectronic{

    private float bluetoothVersion;

    public Headphone(String id, String title,Float bluetoothVersion) {
        super(id, title);
        this.bluetoothVersion=bluetoothVersion;
    }

    public float getBluetoothVersion() {
        return bluetoothVersion;
    }

    public void setBluetoothVersion(float bluetoothVersion) {
        this.bluetoothVersion = bluetoothVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Headphone headphone = (Headphone) o;
        return Float.compare(headphone.bluetoothVersion, bluetoothVersion) == 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                " " + bluetoothVersion +
                " ";
    }
}
