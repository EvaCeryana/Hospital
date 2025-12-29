package com.wia1002.hospital;

public class CustomLinkedList <E>{

    private Node<E> head;
    private int size = 0;

    public void addLast(E data){
        Node<E> n = new Node<>(data);
        if(head == null){

            head = n;
        }
        else{
            Node<E> cur = head;
            while(cur.next != null) cur = cur.next;
            cur.next = n;

        }size++;

    }

    public E removeFirst(){
        if(head == null) return null;

        E data = head.data;
        head = head.next;
        size--;
        return data;

    }

    public Node<E> getHead(){
        return head;

    }

    public boolean isEmpty(){

        return size == 0;
    }

    public int size(){

        return size;
    }
 }
