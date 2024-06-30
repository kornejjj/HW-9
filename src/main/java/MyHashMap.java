
import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> {
    private Node<K, V>[] hashtable;
    private final float loadFactor;
    private int threshold;
    private int counter;
    private Node<K, V> nullKeyNode;  // Для зберігання значення для ключа null

    public MyHashMap() {
        this(16, 0.75f);
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0 || loadFactor <= 0) {
            throw new IllegalArgumentException("Invalid initial capacity or load factor");
        }
        this.loadFactor = loadFactor;
        this.hashtable = new Node[initialCapacity];
        this.threshold = (int) (initialCapacity * loadFactor);
    }

    public MyHashMap<K, V> put(K key, V value) {
        if (key == null) {
            if (nullKeyNode == null) {
                nullKeyNode = new Node<>(null, value);
                counter++;
            } else {
                nullKeyNode.value = value;
            }
            return this;
        }

        int index = indexFor(key.hashCode());
        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> start = hashtable[index];

        if (start == null) {
            hashtable[index] = newNode;
            if (++counter > threshold) {
                resize();
            }
        } else {
            Node<K, V> prev = null;
            while (start != null) {
                if (start.equals(newNode)) {
                    start.value = value;
                    return this;
                }
                prev = start;
                start = start.next;
            }
            prev.next = newNode;
            if (++counter > threshold) {
                resize();
            }
        }
        return this;
    }

    public MyHashMap<K, V> remove(K key) {
        if (key == null) {
            if (nullKeyNode != null) {
                nullKeyNode = null;
                counter--;
            }
            return this;
        }

        int index = indexFor(key.hashCode());
        Node<K, V> start = hashtable[index];

        if (start == null) {
            return this;
        }

        if (start.key.equals(key)) {
            hashtable[index] = start.next;
            counter--;
        } else {
            Node<K, V> prev = start;
            while (start.next != null) {
                if (start.next.key.equals(key)) {
                    start.next = start.next.next;
                    counter--;
                    return this;
                }
                start = start.next;
            }
        }
        return this;
    }

    public void clear() {
        Arrays.fill(hashtable, null);
        nullKeyNode = null;
        counter = 0;
    }

    public int size() {
        return counter;
    }

    public V get(K key) {
        if (key == null) {
            return nullKeyNode == null ? null : nullKeyNode.value;
        }

        int index = indexFor(key.hashCode());
        Node<K, V> start = hashtable[index];

        while (start != null) {
            if (start.key.equals(key)) {
                return start.value;
            }
            start = start.next;
        }
        return null;
    }

    private void resize() {
        int newCapacity = hashtable.length * 2;
        Node<K, V>[] newTable = new Node[newCapacity];
        threshold = (int) (newCapacity * loadFactor);

        for (Node<K, V> node : hashtable) {
            while (node != null) {
                Node<K, V> next = node.next;
                int index = indexFor(node.key.hashCode(), newCapacity);
                node.next = newTable[index];
                newTable[index] = node;
                node = next;
            }
        }
        hashtable = newTable;
    }

    private int indexFor(int hash) {
        return indexFor(hash, hashtable.length);
    }

    private int indexFor(int hash, int length) {
        return Math.abs(hash % length);
    }

    static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
