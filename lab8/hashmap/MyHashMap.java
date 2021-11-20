package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author joon
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int capacity = 16;
    private double loadFactor = .75;
    private int size = 0;
    private HashSet<String> keySet = new HashSet<String>();

    /** Constructors */
    public MyHashMap() {
        this.buckets = createTable(capacity);
    }

    public MyHashMap(int initialSize) {
        this.buckets = createTable(initialSize);
        this.capacity = initialSize;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.buckets = createTable(initialSize);
        this.loadFactor = maxLoad;
        this.capacity = initialSize;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) { return new Collection[tableSize];
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

//    public class HashMapIterator implements Iterator<K> {
//    }

    private int hashIndex(K key, int capacity) {
        int index = key.hashCode() % capacity;
        if (index < 0) {
            return capacity + index;
        }
        return index;
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        buckets = createTable(capacity);
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        int index = hashIndex(key, capacity);
        if (buckets[index] == null) {
            return false;
        }
        for (Node i : buckets[index]) {
            if (i.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int index = hashIndex(key, capacity);
        if (buckets[index] == null) {
            return null;
        }
        for (Node i : buckets[index]) {
            if (i.key.equals(key))  {
                return i.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if ((double)(size/capacity) >= loadFactor) {
            resize();
        }

        int index = hashIndex(key, capacity);
        Node newNode = createNode(key, value);

        if (buckets[index] == null ) {
            Collection<Node> bucket = createBucket();
            bucket.add(newNode);
            buckets[index] = bucket;
            size++;
            return;
        }

        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        buckets[index].add(newNode);
        size++;
    }

    private void resize() {
        Collection<Node>[] oldBuckets = buckets;
        buckets = createTable(capacity * 2);
        capacity*=2;
        size = 0;

        for (int i = 0; i < oldBuckets.length; i++) {
            if (oldBuckets[i] == null) {
                continue;
            }
            for (Node node : oldBuckets[i]) {
                put(node.key, node.value);
            }
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        for (int i = 0; i < capacity; i++) {
            if (buckets[i] == null) {
                continue;
            }
            for (Node node : buckets[i]) {
                keySet.add(node.key.toString());
            }
        }
        return (Set<K>) keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyHashMap<String, String> test = new MyHashMap<>();
        test.put("hello", "0");
        test.put("goodbye", "1");
        System.out.print(test.keySet());

    }
}
