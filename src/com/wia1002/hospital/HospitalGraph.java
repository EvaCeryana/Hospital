package com.wia1002.hospital;

public class HospitalGraph {

    private final int max;
    private final String[] vertices;
    private final double[][] w;
    private int vertexCount;

    public HospitalGraph(int max) {
        this.max = max;
        this.vertices = new String[max];
        this.w = new double[max][max];

        // init weights to infinity (0 on diagonal)
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                w[i][j] = (i == j) ? 0.0 : Double.POSITIVE_INFINITY;
            }
        }
        this.vertexCount = 0;
    }

    public int getVertexCount() { return vertexCount; }

    public String getVertexName(int idx) { return vertices[idx]; }

    public int indexOf(String name) {
        if (name == null) return -1;
        String key = name.trim();
        for (int i = 0; i < vertexCount; i++) {
            if (vertices[i].equalsIgnoreCase(key)) return i;
        }
        return -1;
    }

    private int ensureVertex(String name) {
        int idx = indexOf(name);
        if (idx != -1) return idx;

        if (vertexCount >= max) throw new RuntimeException("Graph full, max=" + max);

        vertices[vertexCount] = name.trim();
        vertexCount++;
        return vertexCount - 1;
    }

    public void addEdge(String a, String b, double cost) {
        int ia = ensureVertex(a);
        int ib = ensureVertex(b);

        // undirected
        if (cost < w[ia][ib]) {
            w[ia][ib] = cost;
            w[ib][ia] = cost;
        }
    }

    public double weight(int i, int j) {
        return w[i][j];
    }
}
