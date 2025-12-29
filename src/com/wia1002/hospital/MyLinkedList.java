package com.wia1002.hospital;

public class MyLinkedList<E> {

    static class Node<E> {
        E data;
        Node<E> next;
        Node(E d) { data = d; }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void addLast(E data) {
        Node<E> n = new Node<>(data);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    public E removeFirst() {
        if (head == null) return null;
        E d = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return d;
    }

    public E peekFirst() {
        return head == null ? null : head.data;
    }

    public void insertSorted(E data, Comparator<E> cmp) {
        Node<E> n = new Node<>(data);

        if (head == null) {
            head = tail = n;
            size++;
            return;
        }

        if (cmp.compare(data, head.data) <= 0) {
            n.next = head;
            head = n;
            size++;
            return;
        }

        Node<E> cur = head;
        while (cur.next != null && cmp.compare(data, cur.next.data) > 0) {
            cur = cur.next;
        }

        n.next = cur.next;
        cur.next = n;
        if (n.next == null) tail = n;
        size++;
    }

    public String toMultilineString() {
        if (head == null) return "(empty)";
        StringBuilder sb = new StringBuilder();
        Node<E> cur = head;
        int i = 1;
        while (cur != null) {
            sb.append(i++).append(". ").append(cur.data).append("\n");
            cur = cur.next;
        }
        return sb.toString();
    }

    public interface Comparator<T> {
        int compare(T a, T b);
    }
}
