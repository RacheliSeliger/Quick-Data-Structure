package il.co.lird.FS133.Projects.QuickDataStructure;

import java.util.Comparator;
import java.util.Iterator;

public class QuickPopDataStructure <T> implements IQuickDataStructure<T>, Iterable<T>  {
    private Node<T> head = null;
    private final Object lock = new Object();
    private final Comparator<? super T> comparator;

    public QuickPopDataStructure(Comparator<? super T> comparator) {

        this.comparator = comparator;
    }

    public QuickPopDataStructure() {
        this.comparator = null;
    }


    //push element in time complexity :  O(n)
    @Override
    public void push(T item) {
        Node<T> newNode = new Node<>(item);

        synchronized (lock) {
            if (head == null) {
                head = newNode;
            } else {

                Node<T> current = head;
                Node<T> prev = current;

                while (current != null && ((Comparable <T>)current.data).compareTo(item) >= 0) {
                    prev = current;
                    current = current.next;
                }

                insertNodeInPlace(newNode, prev, current);
            }
        }
    }


    //pop max element in time complexity :  O(1)
    @Override

    public T pop() {
     synchronized (lock) {
         if (head == null) {
             return null;
         } else {
             T max = head.data;
             head = head.next;
             return max;
         }
     }
    }

    public int count() {

        Node<T> curr = head;
        int count = 0;

        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return (this.head == null);
    }

    //Insert the new node in the right place, in case it's in the head of the list or not
    private void insertNodeInPlace(Node newNode, Node prev, Node current){

        if (current == null){
            prev.next = newNode;
        } else {
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev = newNode;

            if (newNode.prev == null) {
                head = newNode;
            } else {
                prev.next = newNode;
            }
        }
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
