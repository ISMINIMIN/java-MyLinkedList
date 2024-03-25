package mylist;

import java.util.Iterator;
import java.util.ListIterator;

public class MyLinkedList<E> implements MyList<E> {
    private int size;
    private Node first;
    private Node last;

    public MyLinkedList() {
        size = 0;
    }

    private void checkIndex(int index) {
        if(index > size || index < 0)
            throw new IndexOutOfBoundsException();
    }

    Node node(int index) {
        Node curr = first;
        for(int i=0; i<index; i++)
            curr = curr.next;

        return curr;
    }

    private void addFirst(E e) {
        Node newNode = new Node(e);
        newNode.next = first;
        first = newNode;
        size++;

        if(first.next == null)
            last = first;
    }

    private void addLast(E e) {
        Node newNode = new Node(e);

        if(size == 0)
            addFirst(e);
        else {
            last.next = newNode;
            last = newNode;
            size++;
        }
    }

    private E deleteFirst() {
        Node curr = first;
        first = first.next;

        E data = curr.data;
        curr = null;
        size--;

        return data;
    }

    @Override
    public void add(E e) {
        addLast(e);
    }

    @Override
    public void add(int index, E e) {
        checkIndex(index);

        if(index == 0)
            addFirst(e);
        else {
            Node prev = node(index-1);
            Node next = prev.next;

            Node newNode = new Node(e);
            prev.next = newNode;
            newNode.next = next;
            size++;

            if(newNode.next == null)
                last = newNode;
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node curr = node(index);

        return curr.data;
    }

    @Override
    public E delete(int index) {
        checkIndex(index);

        if(index == 0)
            return deleteFirst();

        Node prev = node(index-1);
        Node curr = prev.next;
        prev.next = curr.next;

        E data = curr.data;
        if(curr == last)
            last = prev;
        curr = null;
        size--;

        return data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if(first == null)
            return "empty";

        Node curr = first;
        StringBuilder sb = new StringBuilder();

        while(curr.next != null) {
            sb.append(curr.data).append(", ");
            curr = curr.next;
        }
        sb.append(curr.data);

        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class Node {
        private final E data;
        private Node next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }

        public String toString() {
            return String.valueOf(this.data);
        }
    }

    public class MyIterator implements Iterator<E> {
        private Node lastReturned;
        private Node next;
        private int nextIndex;

        MyIterator() {
            next = first;
            nextIndex = 0;
        }

        public E next() {
            lastReturned = next;
            next = next.next;
            nextIndex++;

            return lastReturned.data;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }
    }
}
