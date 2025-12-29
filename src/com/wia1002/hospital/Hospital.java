package com.wia1002.hospital;

public class Hospital {

    String name;
    String address;
    int capacity;

    public Hospital(String name, String address, int capacity){
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }
    public boolean hasCapacity(){
        return capacity >0;
    }
    public String toString(){
        return name + "|" + address + "| Capacity: " + capacity;
    }

}
