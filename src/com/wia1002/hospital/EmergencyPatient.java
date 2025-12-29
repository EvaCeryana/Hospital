package com.wia1002.hospital;

public class EmergencyPatient extends Patient {
    private final int severity; // 1 best priority

    public EmergencyPatient(String name, int age, char gender, double weight, int severity) {
        super(name, age, gender, weight);
        this.severity = severity;
    }

    public int getSeverity() { return severity; }

    public String toFileLine() {
        return getName() + "," + getAge() + "," + getGender() + "," + getWeight() + "," + severity;
    }

    @Override
    public String toString() {
        return "EmergencyPatient{name='" + getName() + "', age=" + getAge() +
                ", gender=" + getGender() + ", weight=" + getWeight() +
                ", severity=" + severity + "}";
    }
}
