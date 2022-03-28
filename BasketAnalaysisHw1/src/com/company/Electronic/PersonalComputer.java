package com.company.Electronic;

public class PersonalComputer extends AbstractElectronic {
    private short ramCapacity;
    private short ssdCapacity;
    private String cpuType;

    public PersonalComputer(String id, String title, short ramCapacity, short ssdCapacity, String cpuType) {
        super(id, title);
        this.ramCapacity = ramCapacity;
        this.ssdCapacity = ssdCapacity;
        this.cpuType = cpuType;
    }

    public short getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(short ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public short getSsdCapacity() {
        return ssdCapacity;
    }

    public void setSsdCapacity(short ssdCapacity) {
        this.ssdCapacity = ssdCapacity;
    }

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalComputer that = (PersonalComputer) o;
        return ramCapacity == that.ramCapacity && ssdCapacity == that.ssdCapacity && cpuType.equals(that.cpuType);
    }

    @Override
    public String toString() {
        System.out.print(super.toString());
        return
                 ramCapacity +
                " " + ssdCapacity +
                " " + cpuType
                ;
    }
}
