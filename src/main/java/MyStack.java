import java.util.Objects;

public class MyStack<E> {
    protected Node head;
    protected Node end;
    protected int countNodes;
    private MyLinkedList<E> myLinkedList;

    public MyStack() {
        this.head = null;
    }

    public MyStack push(E value) {
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
        return this;
    }

    public MyStack remove(int index) {
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
        return this;
    }


    public MyStack clear() {
        head = null;
        end = null;
        countNodes = 0;
        return this;
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

    class Node {
        protected Node previous;
        protected Node next;
        E data;

    }
}