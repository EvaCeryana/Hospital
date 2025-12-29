package com.wia1002.hospital;

public class DijkstraService {

    public static void findNearestAvailable(
            HospitalGraph graph,
            Hospital[] hospitals,
            int hospitalCount,
            String sourceName
    ) {
        int src = graph.indexOf(sourceName);
        if (src == -1) {
            System.out.println("[ERROR] Source hospital not found in graph: " + sourceName);
            System.out.println("Tip: copy exact name from hospitals.txt");
            return;
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
                double w = graph.weight(u, v);
                if (w == Double.POSITIVE_INFINITY) continue;

                double nd = dist[u] + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    prev[v] = u;
                }
            }
        }

        int bestIdx = -1;
        double bestDist = Double.POSITIVE_INFINITY;

        for (int i = 0; i < n; i++) {
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
            System.out.println("No reachable hospital with available capacity.");
            return;
        }

        Hospital destHospital = findHospitalByName(hospitals, hospitalCount, graph.getVertexName(bestIdx));
        System.out.println("\nNearest available hospital: " + destHospital);
        System.out.println("Total travel time: " + bestDist + " minutes");

        System.out.println("Path:");
        printPath(graph, prev, src, bestIdx);
        System.out.println();
    }

    private static Hospital findHospitalByName(Hospital[] hospitals, int count, String name) {
        if (name == null) return null;
        for (int i = 0; i < count; i++) {
            if (hospitals[i] != null && hospitals[i].getName().equalsIgnoreCase(name.trim())) {
                return hospitals[i];
            }
        }
        return null;
    }

    private static void printPath(HospitalGraph g, int[] prev, int src, int dest) {
        int[] stack = new int[100];
        int top = 0;

        int cur = dest;
        while (cur != -1) {
            stack[top++] = cur;
            if (cur == src) break;
            cur = prev[cur];
        }

        if (stack[top - 1] != src) {
            System.out.println("(No path)");
            return;
        }

        for (int i = top - 1; i >= 0; i--) {
            System.out.print(g.getVertexName(stack[i]));
            if (i != 0) System.out.print(" -> ");
        }
    }
}
