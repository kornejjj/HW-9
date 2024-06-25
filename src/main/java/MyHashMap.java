import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> {
    private final Node<K, V>[] hashtable;
    private final int multiplier;
    private int counter;

    public MyHashMap() {
        this(20);
    }

    public MyHashMap(int multiplier) {
        this.multiplier = multiplier;
        hashtable = new Node[multiplier];
    }

    public MyHashMap<K, V> put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        int index = indexFor(newNode.hashCode());
        Node<K, V> start = hashtable[index];

        if (start == null) {
            hashtable[index] = newNode;
            counter++;
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
            counter++;
        }
        return this;
    }

    public MyHashMap<K, V> remove(K key) {
        Node<K, V> newNode = new Node<>(key, null);
        int index = indexFor(newNode.hashCode());
        Node<K, V> start = hashtable[index];

        if (start == null) {
            return this;
        }

        if (start.equals(newNode)) {
            hashtable[index] = start.next;
            counter--;
        } else {
            Node<K, V> prev = start;
            while (start.next != null) {
                if (start.next.equals(newNode)) {
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
        counter = 0;
    }

    public int size() {
        return counter;
    }

    public V get(K key) {
        Node<K, V> newNode = new Node<>(key, null);
        int index = indexFor(newNode.hashCode());
        Node<K, V> start = hashtable[index];

        if (start == null) {
            return null;
        } else {
            while (start != null) {
                if (start.equals(newNode)) {
                    return start.value;
                }
                start = start.next;
            }
        }
        return null;
    }

    private int indexFor(int hash) {
        return Math.abs(hash % multiplier);
    }

    class Node<K, V> {
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
            return key.equals(node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
