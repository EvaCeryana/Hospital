package com.wia1002.hospital;

public class Hospital {
    private final String name;
    private final String address;
    private int availableCapacity;

    public Hospital(String name, String address, int availableCapacity) {
        this.name = name;
        this.address = address;
        this.availableCapacity = availableCapacity;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public int getAvailableCapacity() { return availableCapacity; }

    public boolean hasCapacity() { return availableCapacity > 0; }

    public String toFileLine() {
        return name + "," + address + "," + availableCapacity;
    }

    @Override
    public String toString() {
        return "Hospital{name='" + name + "', address='" + address + "', capacity=" + availableCapacity + "}";
    }
}
