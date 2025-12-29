package com.wia1002.hospital;

public class NormalQueue {

    private final CustomLinkedList<Patient> list = new CustomLinkedList<>();

    public void enqueue(Patient p){
        list.addLast(p);   // FIFO
    }

    public Patient dequeue(){
        return list.removeFirst();
    }

    public void display(){
        Node<Patient> cur = list.getHead();
        if(cur == null){
            System.out.println("No normal patients.");
            return;
        }
        while(cur != null){
            System.out.println(cur.data);
            cur = cur.next;
        }
    }
}
