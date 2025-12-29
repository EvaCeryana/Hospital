package com.wia1002.hospital;

public class EmergencyPriorityQueue {

    private Node<EmergencyPatient> head;

    public void add(EmergencyPatient p ){
        Node<EmergencyPatient> n = new Node<>(p);

        if(head == null || p.severity <head.data.severity){
            n.next = head;
            head = n;
            return;
        }

        Node<EmergencyPatient> cur = head;
        while(cur.next != null && cur.next.data.severity <= p.severity){
            cur = cur.next;
        }
        n.next = cur.next;
        cur.next = n;
    }

    public EmergencyPatient poll(){
        if(head == null) return null;
        EmergencyPatient p = head.data;
        head = head.next;
        return p;
    }

    public void display(){
        if(head == null){
            System.out.println("No emergency patients");
            return;
        }Node<EmergencyPatient> cur = head;
        while(cur != null){
            System.out.println(cur.data);
            cur = cur.next;
        }
    }
}
