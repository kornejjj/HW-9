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
        Node start = hashtable[indexFor(newNode.hashCode())];

        if (start == null) {
            hashtable[indexFor(newNode.hashCode())] = newNode;
            counter++;
        } else {

            while (start.next != null) {
                if (start.equals(newNode)) {
                    newNode.next = start.next;
                    start = newNode;
                    counter++;
                    return this;
                }
                start = start.next;
            }

            start.next = newNode;
            counter++;
        }
        return this;
    }

    public MyHashMap<K, V> remove(K key) {
        Node newNode = new Node<>(key, null);
        Node start = hashtable[indexFor(newNode.hashCode())];

        if (start == null) {
            return this;
        } else {

            if (start.equals(newNode)) {
                hashtable[indexFor(newNode.hashCode())] = start.next;
                counter--;
            }

            else {
                while (start.next != null) {
                    if (start.next.equals(newNode)) {
                        start.next = start.next.next;
                        counter--;
                        return this;
                    }
                    start = start.next;
                }
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
        Node<K, V> start = hashtable[indexFor(newNode.hashCode())];
        //If key nor exist in table, return null
        if (start == null) {
            return null;
        } else {
            if (start.equals(newNode)) {
                return start.value;
            } else {
                while (start.next != null) {
                    start = start.next;
                    if (start.equals(newNode)) {
                        return start.value;
                    }
                }
            }
        }
        return null;
    }

   
    private int indexFor(int hash) {
        while (hash > multiplier - 1) {
            hash -= multiplier;
        }
        return hash;
    }

    class Node<K, V> {
        private final K key;
        private final V value;
        private Node next;

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
