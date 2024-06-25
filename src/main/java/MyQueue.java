import java.util.Objects;

public class MyQueue<E> {
    private Node<E> head;
    private Node<E> end;
    private int countNodes;

    public MyQueue() {
        this.head = null;
    }

    public MyQueue<E> add(E value) {
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
        return null;
    }


    public MyQueue<E> remove(int index) {
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
        return head.data;
    }


    public E poll() {
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
