package com.wia1002.hospital;

public class Node<E> {
    public E data;
    public Node<E> next;

    public Node(E data) {
        this.data = data;
        this.next = null;
    }
}
