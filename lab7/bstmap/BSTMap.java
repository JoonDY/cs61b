package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node root;
    private int size;

    public class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K k, V v, Node l, Node r) {
            this.key = k;
            this.value = v;
            this.left = l;
            this.right = r;
        }
    }

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    /** Removes all the mappings from this map. */
    public void clear() {
        size = 0;
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return recursiveContains(root, key);
    }

    private boolean recursiveContains(Node node, K key) {
        if (node == null) {
            return false;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return recursiveContains(node.left, key);
        } else if (compare > 0) {
            return recursiveContains(node.right, key);
        } else {
            return true;
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return recursiveGet(root, key);
    }

    private V recursiveGet(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return recursiveGet(node.left, key);
        } else if (compare > 0) {
            return recursiveGet(node.right, key);
        } else {
            return node.value;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        Node newNode = new Node(key, value, null, null);

        if (root == null) {
            root = newNode;
            size++;

        } else {
            Node current = root, parent = null;

            while(true) {
                parent = current;
                if (key.compareTo(current.key) < 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        size++;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        size++;
                        return;
                    }
                }
            }
        }

    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Unsupported");
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException("Unsupported");
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Unsupported");
    }
}
