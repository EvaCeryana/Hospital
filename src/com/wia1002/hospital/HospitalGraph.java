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

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                w[i][j] = (i == j) ? 0.0 : Double.POSITIVE_INFINITY;
            }
        }
        this.vertexCount = 0;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public String getVertexName(int idx) {
        return vertices[idx];
    }

    public int indexOf(String name) {
        if (name == null) return -1;

        String key = name.trim().toLowerCase();
        if (key.isEmpty()) return -1;

        for (int i = 0; i < vertexCount; i++) {
            String v = vertices[i];
            if (v == null) continue;

            String vv = v.trim().toLowerCase();

            if (vv.equals(key)) return i;

            if (vv.contains(key)) return i;

            if (key.contains(vv)) return i;

            if (vv.startsWith("hospital ")) {
                String shortName = vv.substring("hospital ".length()).trim();
                if (shortName.equals(key)) return i;
                if (shortName.contains(key)) return i;
            }
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

        if (cost < w[ia][ib]) {
            w[ia][ib] = cost;
            w[ib][ia] = cost;
        }
    }

    public double weight(int i, int j) {
        return w[i][j];
    }

    public String edgesToMultilineString() {
        if (vertexCount == 0) return "(no graph nodes)";
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = 0; i < vertexCount; i++) {
            for (int j = i + 1; j < vertexCount; j++) {
                double cost = w[i][j];
                if (cost != Double.POSITIVE_INFINITY && cost != 0.0) {
                    sb.append(vertices[i]).append(" <-> ")
                            .append(vertices[j]).append(" = ")
                            .append(cost).append(" minutes\n");
                    count++;
                }
            }
        }

        if (count == 0) return "(no edges)";
        return sb.toString();
    }

    public String verticesToMultilineString() {
        if (vertexCount == 0) return "(no graph nodes)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vertexCount; i++) {
            sb.append(i + 1).append(". ").append(vertices[i]).append("\n");
        }
        return sb.toString();
    }
}
