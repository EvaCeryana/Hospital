package com.wia1002.hospital;

public class FileService {

    public static int loadHospitals(String path, Hospital[] hospitals) throws Exception {
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(path));
        String line;
        int count = 0;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.length() == 0) continue;

            // allow: name OR name,available
            String name;
            boolean available = true;

            String[] parts = line.split(",");
            if (parts.length == 1) {
                name = parts[0].trim();
            } else {
                name = parts[0].trim();
                String av = parts[1].trim().toLowerCase();
                available = av.equals("true") || av.equals("1") || av.equals("yes");
            }

            if (name.length() == 0) continue;

            if (count >= hospitals.length) break;
            hospitals[count++] = new Hospital(name, available);
        }

        br.close();
        return count;
    }

    public static void loadGraph(String path, HospitalGraph graph,
                                 Hospital[] hospitals, int hospitalCount) throws Exception {

        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(path));
        String line;
        int lineNo = 0;

        while ((line = br.readLine()) != null) {
            lineNo++;
            line = line.trim();
            if (line.length() == 0) continue;

            // expected: from,to,cost  (cost can be 15.0)
            String[] parts = line.split(",");
            if (parts.length < 3) {
                System.out.println("[WARN] graph line " + lineNo + " bad format, skipped: " + line);
                continue;
            }

            String fromName = parts[0].trim();
            String toName = parts[1].trim();
            String costStr = parts[2].trim();

            int from = indexOfHospital(hospitals, hospitalCount, fromName);
            int to = indexOfHospital(hospitals, hospitalCount, toName);

            if (from == -1 || to == -1) {
                System.out.println("[WARN] graph line " + lineNo + " unknown hospital name, skipped: " + line);
                continue;
            }

            double cost;
            try {
                cost = Double.parseDouble(costStr);
            } catch (Exception e) {
                System.out.println("[WARN] graph line " + lineNo + " bad cost, skipped: " + line);
                continue;
            }

            graph.addEdge(from, to, cost);
        }

        br.close();
    }

    // ✅ 不用 HashMap：用线性搜索
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
