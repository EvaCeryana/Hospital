package com.wia1002.hospital;

public class HospitalSystemUI {

    private static final String FILE_PATIENTS = "patients.txt";
    private static final String FILE_EMERGENCY = "patients-emergency.txt";
    private static final String FILE_HOSPITALS = "hospitals.txt";
    private static final String FILE_GRAPH = "hospital-graph.txt";

    public void start() throws Exception {
        while (true) {
            System.out.println("\n=== Hospital Patient Management & Triage System ===");
            System.out.println("1) Normal Admissions (Queue) ");
            System.out.println("2) Emergency Triage (Priority Queue) ");
            System.out.println("3) Ambulance Dispatch (Graph + Dijkstra) ");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String c = Console.readLine();

            if ("0".equals(c)) {
                System.out.println("Bye.");
                return;
            } else if ("1".equals(c)) {
                runNormalAdmissions();
            } else if ("2".equals(c)) {
                runEmergencyTriage();
            } else if ("3".equals(c)) {
                runAmbulanceDispatch();
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void runNormalAdmissions() throws Exception {
        MyQueue<Patient> q = new MyQueue<>();
        FileService.loadPatients(FILE_PATIENTS, q);

        while (true) {
            System.out.println("\n--- Normal Admissions (Queue) ---");
            System.out.println("1) View next patient (peek)");
            System.out.println("2) Add patient");
            System.out.println("3) Discharge next patient (poll)");
            System.out.println("4) View all patients");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            String c = Console.readLine();
            if ("0".equals(c)) break;

            if ("1".equals(c)) {
                Patient p = q.peek();
                System.out.println(p == null ? "Queue empty." : p);
            } else if ("2".equals(c)) {
                Patient p = inputPatient(false);
                q.add(p);
                FileService.savePatients(FILE_PATIENTS, q);
                System.out.println("Added + saved.");
            } else if ("3".equals(c)) {
                Patient p = q.remove();
                if (p == null) System.out.println("Queue empty.");
                else {
                    FileService.savePatients(FILE_PATIENTS, q);
                    System.out.println("Discharged: " + p.getName());
                }
            } else if ("4".equals(c)) {
                System.out.println(q.toMultilineString());
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void runEmergencyTriage() throws Exception {
        MyPriorityQueue triage = new MyPriorityQueue();
        FileService.loadEmergencyPatients(FILE_EMERGENCY, triage);

        while (true) {
            System.out.println("\n--- Emergency Triage (Priority Queue) ---");
            System.out.println("1) View next emergency (peek)");
            System.out.println("2) Add emergency patient");
            System.out.println("3) Treat/discharge next (poll)");
            System.out.println("4) View all emergency patients");
            System.out.println("0) Back");
            System.out.print("Choose: ");

            String c = Console.readLine();
            if ("0".equals(c)) break;

            if ("1".equals(c)) {
                EmergencyPatient p = triage.peek();
                System.out.println(p == null ? "Empty." : p);
            } else if ("2".equals(c)) {
                EmergencyPatient p = (EmergencyPatient) inputPatient(true);
                triage.add(p);
                FileService.saveEmergencyPatients(FILE_EMERGENCY, triage);
                System.out.println("Added + saved.");
            } else if ("3".equals(c)) {
                EmergencyPatient p = triage.remove();
                if (p == null) System.out.println("Empty.");
                else {
                    FileService.saveEmergencyPatients(FILE_EMERGENCY, triage);
                    System.out.println("Treated: " + p.getName() + " (severity " + p.getSeverity() + ")");
                }
            } else if ("4".equals(c)) {
                System.out.println(triage.toMultilineString());
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void runAmbulanceDispatch() throws Exception {
        Hospital[] hospitals = new Hospital[50];
        int hospitalCount = FileService.loadHospitals(FILE_HOSPITALS, hospitals);

        HospitalGraph graph = new HospitalGraph(50);
        FileService.loadGraph(FILE_GRAPH, graph);

        System.out.println("\n--- Ambulance Dispatch (Dijkstra) ---");
        System.out.println("Hospitals loaded: " + hospitalCount);
        System.out.println("Graph nodes: " + graph.getVertexCount());

        System.out.print("Enter source hospital name exactly (copy from hospitals.txt): ");
        String source = Console.readLine();

        DijkstraService.findNearestAvailable(graph, hospitals, hospitalCount, source);
    }

    private Patient inputPatient(boolean emergency) throws Exception {
        System.out.print("Name: ");
        String name = Console.readLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(Console.readLine());

        System.out.print("Gender (M/F): ");
        char g = Console.readLine().trim().charAt(0);

        System.out.print("Weight (e.g. 75.5): ");
        double w = Double.parseDouble(Console.readLine());

        if (!emergency) return new Patient(name, age, g, w);

        System.out.print("Severity (1=Critical ... 5=Stable): ");
        int s = Integer.parseInt(Console.readLine());
        return new EmergencyPatient(name, age, g, w, s);
    }
}
