package deque;


import java.util.Iterator;


public class LinkedListDeque<T> implements Iterable<T>, Deque<T>{
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node((T)"null", null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    private class Node{
        public Node prev;
        public T item;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private Node pos;

        public DequeIterator() {
            pos = sentinel;
        }

        public boolean hasNext() {
            return pos.next != sentinel;
        }

        public T next() {
            T returnItem = pos.next.item;
            pos = pos.next;
            return returnItem;
        }
    }


    public void addFirst(T x) {
        sentinel.next = new Node(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T x) {
        sentinel.prev = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return returnItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return returnItem;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int i = 0;
        Node current = sentinel.next;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getHelper(index, sentinel.next, 0);
    }

    private T getHelper(int index, Node node, int curr) {
        if (curr == index) {
            return node.item;
        }
        return getHelper(index, node.next, curr + 1);
    }

    public void printDeque() {
        StringBuilder SB = new StringBuilder();
        for (T x : this) {
            SB.append(x.toString());
            SB.append(" ");
        }
        SB.append("\n");
        System.out.println(SB);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Deque)) {
            return false;
        }
        LinkedListDeque<T> compare = (LinkedListDeque<T>) other;
        if (compare.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size - 1; i++) {
            if (compare.get(i) != this.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }
}
