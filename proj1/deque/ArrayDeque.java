package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T>{
    private T[] items;
    private int size, front, back;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        front = back = -1;
    }

    public ArrayDeque(int i) {
        items = (T []) new Object[i];
        size = 0;
        front = back = -1;
    }

    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private int pos;

        public DequeIterator() {
            pos = front;
        }

        public boolean hasNext() {
            return items[pos] != null;
        }

        public T next() {
            T returnItem = items[pos];
            pos++;
            return returnItem;
        }
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize((int) Math.round(size * 1.1));
        }

        if (front == -1) {
            front = 0;
            back = 0;
        } else if (front == 0) {
            front = (items.length - 1);
        } else {
            front--;
        }

        items[front] = x;

        size ++;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize((int) Math.round(size * 1.1));
        }
        if (back == -1) {
            front = 0;
            back = 0;
        } else {
            back = (back + 1) % items.length;
        }

        items[back] = x;

        size ++;
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        if (front >= back) {
            int section = items.length - front;
            System.arraycopy(items, front, a, 0, section);
            System.arraycopy(items, 0, a, section, back + 1);
        } else {
            System.arraycopy(items, front, a, 0, size);
        }
        front = 0;
        back = size - 1;

        items = a;
    }

    private void downsize() {
        if (size >= 16 && ((double) (size - 1)/ items.length) <= .25) {
            resize((int) Math.round(items.length * .5));
        }
    }

    public double usage() {
        return (double) size/ items.length;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        // Checks to see if array needs to be shrunk
        downsize();
        T returnItem = items[front];
        items[front] = null;
        if (front == back) {
            front = -1;
            back = -1;
        } else {
            front = (front + 1) % items.length;
        }
        size--;
        return returnItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        // Checks to see if array needs to be shrunk
        downsize();
        T returnItem = items[back];
        items[back] = null;
        if (back == front) {
            back = -1;
            front = -1;
        } else if (back == 0) {
            back = items.length-1;
        } else {
            back--;
        }
        size--;
        return returnItem;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int calcIndex = (front + index) % items.length;
        return items[calcIndex];
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

        Deque<T> compare = (Deque<T>) other;
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
