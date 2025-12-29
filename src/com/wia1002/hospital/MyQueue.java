package com.wia1002.hospital;

public class MyQueue<E> {
    private final MyLinkedList<E> list = new MyLinkedList<>();

    public void add(E e) { list.addLast(e); }
    public E remove() { return list.removeFirst(); }
    public E peek() { return list.peekFirst(); }
    public boolean isEmpty() { return list.isEmpty(); }
    public int size() { return list.size(); }

    public String toMultilineString() { return list.toMultilineString(); }

    MyLinkedList<E> internalList() { return list; }
}
