package algdagtatype;

import java.util.*;

public class RandomizedQueue<T> implements Iterable<T> {
    // fields
    private Map<Integer, T> idxMap;
    private int size;

    private class RandomizedQueueIterator<T> implements Iterator<T> {
        int[] seq;
        int i;
        public RandomizedQueueIterator() {
            seq = new int[size];
            i = 0;
            for (i = 1; i < size; ++i) {
                seq[i] = i;
            }
            int rand;
            for (i = 0; i < size - 1; ++i) {
                rand = (int) (Math.random() * (size - i)) + i;
                swap(rand);
            }
            i = 0;
        }

        public boolean hasNext() {
            return i < size;
        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("not supported");
        }

        public T next() throws NoSuchElementException {
            if (i == size) throw new NoSuchElementException("Reached end");
            return (T) idxMap.get(seq[i++]);
        }

        private void swap(int r) {
            int tmp = seq[i];
            seq[i] = seq[r];
            seq[r] = tmp;
        }
    }

    // methods
    public RandomizedQueue() {
        idxMap = new HashMap<Integer, T>();
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T item) throws IllegalArgumentException {
        if (item == null) throw new IllegalArgumentException("input is null");
        idxMap.put(size, item);
        size++;
    }

    public T dequeue() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException("Queue is empty");
        int random = (int) (Math.random() * size);
        T deqItem = idxMap.get(random);
        idxMap.put(random, idxMap.get(size - 1));
        idxMap.remove(size - 1);
        size--;
        return deqItem;
    }

    public T sample() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException("Queue is empty");
        int random = (int) (Math.random() * size);
        return idxMap.get(random);
    }

    @Override
    public Iterator<T> iterator() {
        return new RandomizedQueueIterator<T>();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int k = 0; k < 25; ++k) {
            rq.enqueue(k);
        }
        Iterator<Integer> it = rq.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
        System.out.println();

        for (int k = 0; k < 15; ++k) {
            System.out.print(rq.dequeue() + ", ");
        }
        System.out.println();

        it = rq.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
        System.out.println();
    }
}
