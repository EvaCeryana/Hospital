package com.wia1002.hospital;

public class MyPriorityQueue {
    private final MyLinkedList<EmergencyPatient> list = new MyLinkedList<>();

    public void add(EmergencyPatient p) {
        list.insertSorted(p, new MyLinkedList.Comparator<EmergencyPatient>() {
            @Override
            public int compare(EmergencyPatient a, EmergencyPatient b) {
                // smaller severity first
                return a.getSeverity() - b.getSeverity();
            }
        });
    }

    public EmergencyPatient remove() { return list.removeFirst(); }
    public EmergencyPatient peek() { return list.peekFirst(); }
    public boolean isEmpty() { return list.isEmpty(); }
    public int size() { return list.size(); }

    public String toMultilineString() { return list.toMultilineString(); }

    MyLinkedList<EmergencyPatient> internalList() { return list; }
}
