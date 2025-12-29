package com.wia1002.hospital;

public class HospitalGraph {

    static final int Max = 50;

    String[] names = new String[Max];
    double[][] weight = new double[Max][Max];
    int size = 0;

    public void addHospital (String name){
        if (indexOf(name)== -1) {
        }
    }
    public void addEdge(String a, String b, double w) {
        addHospital(a);
        addHospital(b);

        int i = indexOf(a);
        int j = indexOf(b);

        weight[i][j] = w;
        weight[j][i] = w;
    }

    public int indexOf(String name) {
        for (int i = 0; i < size; i++) {
            if (names[i].equals(name)) return i;
        }
        return -1;
    }
}
