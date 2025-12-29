package com.wia1002.hospital;

public class Hospital {
    private final String name;
    private boolean available;

    public Hospital(String name, boolean available) {
        this.name = name;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
