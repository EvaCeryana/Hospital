package com.wia1002.hospital;

public class Patient {

    String name;
    int age;
    char gender;
    double weight;

    public Patient(String name, int age, char gender, double weight){

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
    }

    public String toString(){

        return name + "| Age: " + age + "|" + gender + "|" + weight  + "kg";
     }
}
