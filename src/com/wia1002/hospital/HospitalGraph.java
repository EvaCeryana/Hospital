package com.wia1002.hospital;

public class HospitalGraph {
    private final int max;
    private final double[][] w;

    public HospitalGraph(int max) {
        this.max = max;
        this.w = new double[max][max];

        // init INF
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                w[i][j] = (i == j) ? 0.0 : Double.POSITIVE_INFINITY;
            }
        }
    }

    public void addEdge(int from, int to, double cost) {
        if (from < 0 || from >= max || to < 0 || to >= max) {
            // ✅ 防止 ArrayIndexOutOfBounds
            return;
        }
        if (cost < 0) return;

        // undirected graph (ambulance travel both ways)
        w[from][to] = cost;
        w[to][from] = cost;
    }

    public double getCost(int from, int to) {
        if (from < 0 || from >= max || to < 0 || to >= max) {
            return Double.POSITIVE_INFINITY;
        }
        return w[from][to];
    }

    public int size() {
        return max;
    }
}
