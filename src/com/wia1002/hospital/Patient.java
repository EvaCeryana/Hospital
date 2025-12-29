package com.wia1002.hospital;

public class Patient {
    private final String name;
    private final int age;
    private final char gender;
    private final double weight;

    public Patient(String name, int age, char gender, double weight) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public char getGender() { return gender; }
    public double getWeight() { return weight; }

    public String toFileLine() {
        return name + "," + age + "," + gender + "," + weight;
    }

    @Override
    public String toString() {
        return "Patient{name='" + name + "', age=" + age + ", gender=" + gender + ", weight=" + weight + "}";
    }
}
