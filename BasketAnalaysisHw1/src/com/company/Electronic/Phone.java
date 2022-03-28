package com.company.Electronic;

import java.util.Objects;

public class Phone extends AbstractElectronic {
    private short memoryCapacity; //short is used to decrease avoid unnecessary memory consumption.

    public Phone(String id, String title, short memoryCapacity) {
        super(id, title);
        this.memoryCapacity = memoryCapacity;
    }


    public short getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(short memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return memoryCapacity == phone.memoryCapacity;
    }

    @Override
    public String toString() {
        return super.toString() +
                " " + memoryCapacity +
                "  ";
    }
}
