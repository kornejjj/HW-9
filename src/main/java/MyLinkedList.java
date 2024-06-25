import java.util.Objects;

public class MyLinkedList<E> {
    private Node<E> head;
    private Node<E> end;
    private int countNodes;

    public MyLinkedList() {
        this.head = null;
    }


    public void add(E value) {
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


    public E get(int index) {
        Node<E> currentNode;

        if (((countNodes - index) / (countNodes * 1.0)) < 0.5) {
            currentNode = end;
            while (index != countNodes - 1) {
                index++;
                currentNode = currentNode.previous;
            }
        }

        else {
            currentNode = head;
            while (index != 0) {
                index--;
                currentNode = currentNode.next;
            }
        }

        return currentNode.data;
    }

    private static class Node<E> {
        Node<E> previous;
        Node<E> next;
        E data;

    }
}
