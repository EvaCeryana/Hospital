package com.wia1002.hospital;

public class Main {

    public static void main(String[] args) throws Exception {

        Hospital[] hospitals = new Hospital[50];

        // ✅ 让 loadHospitals 返回真正数量，避免你手写 8 但文件不一致
        int hospitalCount = FileService.loadHospitals("hospitals.txt", hospitals);

        HospitalGraph graph = new HospitalGraph(50);

        FileService.loadGraph("hospital-graph.txt", graph, hospitals, hospitalCount);

        System.out.println("Source hospital:");
        byte[] buf = new byte[100];
        int len = System.in.read(buf);
        String source = new String(buf, 0, len).trim();

        DijkstraService.findNearestAvailable(
                graph,
                hospitals,
                hospitalCount,
                source
        );
    }
}
