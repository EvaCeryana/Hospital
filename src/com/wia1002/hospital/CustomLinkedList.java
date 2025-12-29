package com.wia1002.hospital;

public class CustomLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void addLast(E data) {
        Node<E> n = new Node<>(data);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
    }

    public E removeFirst() {
        if (head == null) return null;
        E d = head.data;
        head = head.next;
        if (head == null) tail = null;
        return d;
    }

    public E peek() {
        return head == null ? null : head.data;
    }

    public Node<E> getHead() {
        return head;
    }

    public void addFirst(E data) {
        Node<E> n = new Node<>(data);
        if (head == null) {
            head = tail = n;
        } else {
            n.next = head;
            head = n;
        }
    }
}
