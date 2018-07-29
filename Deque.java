package algdagtatype;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // fields
    private Node<Item> first, last;
    private int size;

    private class Node<Item> {
        // fields
        Item label;
        Node prev, next;

        // methods
        public Node(Item x) {
            label = x;
            prev = null;
            next = null;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> curr;

        public DequeIterator() {
            curr = first;
        }

        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("no supporting remove operation");
        }

        public boolean hasNext() {
            return curr != null;
        }

        public Item next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("no such element");
            Item it = curr.label;
            curr = curr.next;
            return it;
        }
    }

    // methods
    public Deque () { // construct an empty deque
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {// is the deque empty?
        return size == 0;
    }

    public int size() { // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) throws IllegalArgumentException { // add the item to the front
        if (item == null) throw new IllegalArgumentException("input is null");
        Node<Item> newFirst = new Node<>(item);
        if (first == null) {
            last = newFirst;
        } else {
            first.prev = newFirst;
        }
        newFirst.next = first;
        first = newFirst;
        size++;
    }

    public void addLast(Item item) throws IllegalArgumentException {
        if (item == null) throw new IllegalArgumentException("input is null");
        Node<Item> newLast = new Node<>(item);
        if (last == null) {
            first = newLast;
        } else {
            last.next = newLast;
        }
        newLast.prev = first;
        last = newLast;
        size++;
    }          // add the item to the end

    public Item removeFirst() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("no such element");
        size--;
        Node<Item> old = first;
        first = first.next;
        if (size == 0) {
            last = null;
        } else {
            first.prev = null;
            old.next = null;
        }
        return old.label;
    }               // remove and return the item from the front

    public Item removeLast() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("no such element");
        size--;
        Node<Item> old = last;
        last = last.prev;
        if (size == 0) {
            first = null;
        } else {
            last.next = null;
            old.prev = null;
        }
        return old.label;
    }                // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }        // return an iterator over items in order from front to end

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("empty");
        deque.addFirst("first");
        deque.addLast("last");
        Iterator<String> it = deque.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
    }  // unit testing (optional)
}
