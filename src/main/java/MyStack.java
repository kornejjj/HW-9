import java.util.Objects;

public class MyStack<E> {
    private Node<E> head;
    private Node<E> end;
    private int countNodes;
    private MyLinkedList<E> myLinkedList;

    public MyStack() {
        this.head = null;
    }

    public void push(E value) {
        Node<E> node = new Node<>();
        node.data = value;

        if (Objects.isNull(head)) {
            head = node;
            end = node;
        }

        else {
            end.next = node;
            node.previous = end;
            end = node;
        }
        countNodes++;
    }

    public void remove(int index) {
        Node<E> currentNode = head;

        while (index != 0) {
            index--;
            currentNode = currentNode.next;
        }

        if (currentNode == head) {
            head = head.next;
            countNodes--;
        }

        else if (currentNode == end) {
            end = currentNode.previous;
            countNodes--;
        }

        else {
            currentNode.previous.next = currentNode.next;
            currentNode.next.previous = currentNode.previous;
            countNodes--;
        }
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
        return end.data;
    }


    public E pop() {
        E result = end.data;
        this.remove(countNodes - 1);
        return result;
    }

    class Node<E> {
        protected Node<E> previous;
        protected Node<E> next;
        E data;

    }
}