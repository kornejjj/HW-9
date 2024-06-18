import java.util.Objects;

public class MyQueue<E> {
    private Node<E> head;
    private Node<E> end;
    private int countNodes;

    public MyQueue() {
        this.head = null;
    }

    public MyQueue<E> add(E value) {
        Node node = new Node();
        node.data = value;
        //if list is empty storage in head
        if (Objects.isNull(head)) {
            head = node;
            end = node;
        }
        // if list is not empty storage in the end
        else {
            end.next = node;
            node.previous = end;
            end = node;
        }
        countNodes++;
        return null;
    }


    public MyQueue<E> remove(int index) {
        Node currentNode = head;
        //looks for an item with an index
        while (index != 0) {
            index--;
            currentNode = currentNode.next;
        }
        // if the index = 0, delete the head
        if (currentNode == head) {
            head = head.next;
            countNodes--;
        }
        // if the index = last element, delete the end
        else if (currentNode == end) {
            end = currentNode.previous;
            countNodes--;
        }
        //if the index  is middle element
        else {
            currentNode.previous.next = currentNode.next;
            currentNode.next.previous = currentNode.previous;
            countNodes--;
        }

        return null;
    }


    public void clear() {
        head = null;
        end = null;
        countNodes = 0;

    }


    public int size() {
        return countNodes;
    }


    public E peek() {
        return (E) head.data;
    }


    public E pool() {
        E result = (E) head.data;
        this.remove(0);
        return result;
    }

    private static class Node<E> {
        Node<E> previous;
        Node<E> next;
        E data;

    }

}
