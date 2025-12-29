package com.wia1002.hospital;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileService {

    public static void loadPatients(String filename, MyQueue<Patient> q) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int lineNo = 0;

        while ((line = br.readLine()) != null) {
            lineNo++;
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] p = splitCsv(line);
            if (p.length != 4) {
                System.out.println("[WARN] patients line " + lineNo + " invalid: " + line);
                continue;
            }

            String name = p[0];
            int age = Integer.parseInt(p[1].trim());
            char gender = p[2].trim().charAt(0);
            double weight = Double.parseDouble(p[3].trim());

            q.add(new Patient(name, age, gender, weight));
        }
        br.close();
    }

    public static void savePatients(String filename, MyQueue<Patient> q) throws Exception {
        FileWriter fw = new FileWriter(filename, false);

        MyLinkedList<Patient> list = q.internalList();
        MyLinkedList.Node<Patient> cur = getHead(list);

        while (cur != null) {
            fw.write(cur.data.toFileLine());
            fw.write("\n");
            cur = cur.next;
        }
        fw.close();
    }

    public static void loadEmergencyPatients(String filename, MyPriorityQueue pq) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int lineNo = 0;

        while ((line = br.readLine()) != null) {
            lineNo++;
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] p = splitCsv(line);
            if (p.length != 5) {
                System.out.println("[WARN] emergency line " + lineNo + " invalid: " + line);
                continue;
            }

            String name = p[0];
            int age = Integer.parseInt(p[1].trim());
            char gender = p[2].trim().charAt(0);
            double weight = Double.parseDouble(p[3].trim());
            int severity = Integer.parseInt(p[4].trim());

            pq.add(new EmergencyPatient(name, age, gender, weight, severity));
        }
        br.close();
    }

    public static void saveEmergencyPatients(String filename, MyPriorityQueue pq) throws Exception {
        FileWriter fw = new FileWriter(filename, false);

        MyLinkedList<EmergencyPatient> list = pq.internalList();
        MyLinkedList.Node<EmergencyPatient> cur = getHead(list);

        while (cur != null) {
            fw.write(cur.data.toFileLine());
            fw.write("\n");
            cur = cur.next;
        }
        fw.close();
    }

    public static int loadHospitals(String filename, Hospital[] hospitals) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int count = 0;
        int lineNo = 0;

        while ((line = br.readLine()) != null) {
            lineNo++;
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] p = splitCsv(line);
            if (p.length != 3) {
                System.out.println("[WARN] hospitals line " + lineNo + " invalid: " + line);
                continue;
            }

            String name = p[0].trim();
            String addr = p[1].trim();
            int cap = Integer.parseInt(p[2].trim());

            hospitals[count++] = new Hospital(name, addr, cap);
        }
        br.close();
        return count;
    }

    public static void loadGraph(String filename, HospitalGraph graph) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int lineNo = 0;

        while ((line = br.readLine()) != null) {
            lineNo++;
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] p = splitCsv(line);
            if (p.length != 3) {
                System.out.println("[WARN] graph line " + lineNo + " invalid: " + line);
                continue;
            }

            String a = p[0].trim();
            String b = p[1].trim();

            double t;
            try {
                t = Double.parseDouble(p[2].trim());
            } catch (Exception ex) {
                System.out.println("[WARN] graph line " + lineNo + " bad cost, skipped: " + line);
                continue;
            }

            graph.addEdge(a, b, t);
        }
        br.close();
    }


    private static String[] splitCsv(String line) {
        return line.split(",");
    }

    @SuppressWarnings("unchecked")
    private static <T> MyLinkedList.Node<T> getHead(MyLinkedList<T> list) throws Exception {
        java.lang.reflect.Field f = MyLinkedList.class.getDeclaredField("head");
        f.setAccessible(true);
        return (MyLinkedList.Node<T>) f.get(list);
    }
}
