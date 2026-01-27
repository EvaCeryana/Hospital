package com.wia1002.hospital;

public class DijkstraService {

    public static void findNearestAvailable(
            HospitalGraph graph,
            Hospital[] hospitals,
            int hospitalCount,
            String sourceName
    ) {
        String report = findNearestAvailableReport(graph, hospitals, hospitalCount, sourceName);
        System.out.print(report);
    }

    public static String findNearestAvailableReport(
            HospitalGraph graph,
            Hospital[] hospitals,
            int hospitalCount,
            String sourceName
    ) {
        int src = graph.indexOf(sourceName);
        if (src == -1) {
            return "[ERROR] Source hospital not found: " + sourceName + "\nTip: copy exact name from hospitals.txt\n";
        }

        int n = graph.getVertexCount();
        double[] dist = new double[n];
        boolean[] used = new boolean[n];
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
            used[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0.0;

        for (int step = 0; step < n; step++) {
            int u = -1;
            double best = Double.POSITIVE_INFINITY;

            for (int i = 0; i < n; i++) {
                if (!used[i] && dist[i] < best) {
                    best = dist[i];
                    u = i;
                }
            }

            if (u == -1) break;
            used[u] = true;

            for (int v = 0; v < n; v++) {
                double ww = graph.weight(u, v);
                if (ww == Double.POSITIVE_INFINITY) continue;

                double nd = dist[u] + ww;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    prev[v] = u;
                }
            }
        }

        int bestIdx = -1;
        double bestDist = Double.POSITIVE_INFINITY;

        for (int i = 0; i < n; i++) {
            if (i == src) continue;

            String name = graph.getVertexName(i);
            Hospital h = findHospitalByName(hospitals, hospitalCount, name);

            if (h != null && h.hasCapacity()) {
                if (dist[i] < bestDist) {
                    bestDist = dist[i];
                    bestIdx = i;
                }
            }
        }

        if (bestIdx == -1 || bestDist == Double.POSITIVE_INFINITY) {
            return "No reachable hospital with available capacity.\n";
        }

        Hospital destHospital = findHospitalByName(hospitals, hospitalCount, graph.getVertexName(bestIdx));

        StringBuilder sb = new StringBuilder();
        sb.append("\nNearest available hospital: ").append(destHospital).append("\n");
        sb.append("Total travel time: ").append(bestDist).append(" minutes\n");
        sb.append("Path: ").append(pathToString(graph, prev, src, bestIdx)).append("\n");
        return sb.toString();
    }

    private static Hospital findHospitalByName(Hospital[] hospitals, int count, String name) {
        if (name == null) return null;
        String key = name.trim();

        for (int i = 0; i < count; i++) {
            Hospital h = hospitals[i];
            if (h != null && h.getName() != null && h.getName().trim().equalsIgnoreCase(key)) {
                return h;
            }
        }
        return null;
    }

    private static String pathToString(HospitalGraph g, int[] prev, int src, int dest) {
        int[] stack = new int[200];
        int top = 0;

        int cur = dest;
        while (cur != -1) {
            if (top >= stack.length) break;
            stack[top++] = cur;
            if (cur == src) break;
            cur = prev[cur];
        }

        if (top == 0 || stack[top - 1] != src) return "(No path)";

        StringBuilder sb = new StringBuilder();
        for (int i = top - 1; i >= 0; i--) {
            sb.append(g.getVertexName(stack[i]));
            if (i != 0) sb.append(" -> ");
        }
        return sb.toString();
    }
}
