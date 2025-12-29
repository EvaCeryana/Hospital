package com.wia1002.hospital;

public class DijkstraService {

    public static void findNearestAvailable(HospitalGraph graph,
                                            Hospital[] hospitals,
                                            int hospitalCount,
                                            String sourceName) {

        int src = indexOfHospital(hospitals, hospitalCount, sourceName);
        if (src == -1) {
            System.out.println("Source hospital not found: " + sourceName);
            return;
        }

        int n = hospitalCount;
        double[] dist = new double[n];
        boolean[] vis = new boolean[n];

        for (int i = 0; i < n; i++) dist[i] = Double.POSITIVE_INFINITY;
        dist[src] = 0.0;

        for (int it = 0; it < n; it++) {
            int u = -1;
            double best = Double.POSITIVE_INFINITY;

            for (int i = 0; i < n; i++) {
                if (!vis[i] && dist[i] < best) {
                    best = dist[i];
                    u = i;
                }
            }

            if (u == -1) break;
            vis[u] = true;

            for (int v = 0; v < n; v++) {
                double w = graph.getCost(u, v);
                if (w == Double.POSITIVE_INFINITY) continue;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // find nearest available (excluding source)
        int ans = -1;
        double ansDist = Double.POSITIVE_INFINITY;

        for (int i = 0; i < n; i++) {
            if (i == src) continue;
            if (hospitals[i] == null) continue;
            if (!hospitals[i].isAvailable()) continue;
            if (dist[i] < ansDist) {
                ansDist = dist[i];
                ans = i;
            }
        }

        if (ans == -1 || ansDist == Double.POSITIVE_INFINITY) {
            System.out.println("No available hospital reachable from: " + hospitals[src].getName());
        } else {
            System.out.println("Nearest available hospital: " + hospitals[ans].getName());
            System.out.println("Distance: " + ansDist);
        }
    }

    private static int indexOfHospital(Hospital[] hospitals, int hospitalCount, String name) {
        if (name == null) return -1;
        String key = name.trim();

        for (int i = 0; i < hospitalCount; i++) {
            if (hospitals[i] == null) continue;
            if (hospitals[i].getName().trim().equalsIgnoreCase(key)) {
                return i;
            }
        }
        return -1;
    }
}
