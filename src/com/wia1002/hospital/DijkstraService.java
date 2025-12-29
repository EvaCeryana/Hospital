package com.wia1002.hospital;

public class DijkstraService {

    public static void findNearestAvailable(
            HospitalGraph g,
            Hospital[] hospitals,
            int hospitalCount,
            String source) {

        int n = g.size;
        double[]dist = new double[n];
        boolean[] visited = new boolean[n];

        int src = g.indexOf(source);
        if(src == -1){
            System.out.println("Source hospital not found.");
            return;
        }
        for(int i =0 ; i < n; i++){
            dist[i] = Double.MAX_VALUE;
            visited[i] = false;
        }
        dist[src] = 0;

        for(int step= 0; step < n; step++){
            int u = -1;
            double min = Double.MAX_VALUE;

            for(int i = 0; i < n; i++){
                if(!visited[i] && dist[i] < min){
                    min = dist[i];
                    u = i;
                }
            }

            if(u == -1) break;
            visited[u] = true;

            for(int h = 0; h < hospitalCount; h++){
                if(hospitals[h].name.equals(g.names[u])
                && hospitals[h].capacity>0){
                    System.out.println("Nearest hospital:");
                    System.out.println(hospitals[h]);
                    System.out.println("Travel time: " + dist[u] + " minutes");
                    return;
                }
            }
            for(int v = 0; v < n ; v++){
                if (!visited[v]
                        && g.weight[u][v] > 0
                        && dist[u] + g.weight[u][v] < dist[v]) {

                    dist[v] = dist[u] + g.weight[u][v];
                }
            }
        }
        System.out.println("No hospital with available capacity.");


    }

}
