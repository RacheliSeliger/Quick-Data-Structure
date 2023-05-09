package il.co.lird.FS133.Projects.QuickDataStructure;

import java.util.Comparator;
import java.util.Iterator;

public class QuickPushDataStructure <T> implements IQuickDataStructure<T>, Iterable<T>{
    private Node<T> maxNode = null;
    private Node<T> head = null;
    private final Object lock = new Object();
    private final Comparator<? super T> comparator ;


    public QuickPushDataStructure(Comparator<? super T> comparator) {

        this.comparator = comparator;
    }

    public QuickPushDataStructure() {

        this.maxNode = null;
        this.head = null;
        this.comparator = null;
    }

    //Push element in time complexity :  O(1)
    @Override

    public void push(T item) {

        Node<T> newNode = new Node<>(item);
        synchronized (lock) {
            if (head == null) {
                head = newNode;
            }
            else
            {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            if (maxNode == null || ((Comparable <T>)item).compareTo(maxNode.data) > 0) {
                maxNode = newNode;
            }
        }
    }

    //Pop element in time complexity :  O(n)
    @Override

    public T pop() {

        synchronized (lock) {
            if (head == null) {
                return null;
            }

            T item = removeMaxNode();
            if (item.equals(maxNode.data)) {
                updateMaxNode();
            }

            return item;
        }
    }

    public T getMax() {

        synchronized (lock) {
            return maxNode.data;
        }
    }

    public int count() {

        int count = 0;
        Node<T> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return (this.head == null);
    }

    private void updateMaxNode() {

        if (head == null) {
            maxNode = null;
            return;
        }
        Node<T> newMaxNode = head;
        Node<T> curr = head.next;

        while (curr != null) {

            if (((Comparable <T>)curr.data).compareTo(newMaxNode.data) > 0) {
                newMaxNode = curr;
            }
            curr = curr.next;

        }

        maxNode = newMaxNode;
    }

    private T removeMaxNode(){

        if (maxNode == head) {
            head = head.next;

        } else {

            maxNode.prev.next = maxNode.next;
            if (maxNode.next != null) {
                maxNode.next.prev = maxNode.prev;
            }
        }
        return maxNode.data;
    }


    //Provide iterator to iterate the list
    @Override
    public Iterator<T> iterator() {
        return new QuickListIterator();
    }
    private class QuickListIterator implements Iterator<T> {
        private Node<T> curr;

        public QuickListIterator() {
            this.curr = head;
        }

        @Override
        public boolean hasNext() {

            return (this.curr != null);
        }


        @Override
        public T next() {
            T item = curr.data;
            curr = curr.next;
            return item;
        }
    }


    private static class Node<T> {
        private T data ;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

}
