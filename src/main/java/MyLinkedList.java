import java.util.Objects;

public class MyLinkedList<E> {
    private Node<E> head;
    private Node<E> end;
    private int countNodes;

    public MyLinkedList() {
        this.head = null;
    }


    public void add(E value) {
        Node node = new Node();
        node.data = value;
        //if list is empty storage in head
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
        Node currentNode;
        // if index more close to the end, looking from the end
        if (((countNodes - index) / (countNodes * 1.0)) < 0.5) {
            currentNode = end;
            while (index != countNodes - 1) {
                index++;
                currentNode = currentNode.previous;
            }
        }
        // if index more close to the head, looking from the head
        else {
            currentNode = head;
            while (index != 0) {
                index--;
                currentNode = currentNode.next;
            }
        }

        return (E) currentNode.data;
    }

    private static class Node<E> {
        Node<E> previous;
        Node<E> next;
        E data;

    }
}
