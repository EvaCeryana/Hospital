package com.wia1002.hospital;

public class EmergencyPriorityQueue {

    private final CustomLinkedList<EmergencyPatient> list;

    public EmergencyPriorityQueue() {
        list = new CustomLinkedList<>();
    }

    public void add(EmergencyPatient p) {

        if (list.isEmpty()) {
            list.addFirst(p);
            return;
        }

        if (p.getSeverity() < list.peek().getSeverity()) {
            list.addFirst(p);
            return;
        }

        Node<EmergencyPatient> cur = list.getHead();

        while (cur.next != null &&
                cur.next.data.getSeverity() <= p.getSeverity()) {
            cur = cur.next;
        }

        Node<EmergencyPatient> n = new Node<>(p);
        n.next = cur.next;
        cur.next = n;
    }

    public EmergencyPatient remove() {
        return list.removeFirst();
    }

    public EmergencyPatient peek() {
        return list.peek();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void display() {
        if (list.isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return;
        }

        Node<EmergencyPatient> cur = list.getHead();
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
    }
}
