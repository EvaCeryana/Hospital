package com.wia1002.hospital;

public class EmergencyPatient extends Patient{
    int severity;

    public EmergencyPatient(String name, int age, char gender, double weight, int severity){

        super(name, age, gender, weight);
        this.severity = severity;
    }

    @Override
    public String toString() {
        return super.toString() + "| Severity: " + severity;
    }
}
