package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private final SimpleArrayList<T> set = new SimpleArrayList<>(0);
    private int modCount;

    @Override
    public boolean add(T value) {
        boolean isAdded = !contains(value);
        if (isAdded) {
            set.add(value);
            modCount++;
        }
        return isAdded;
    }

    @Override
    public boolean contains(T value) {
        boolean isFound = false;
        for (T item : set) {
            if (Objects.equals(item, value)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final Iterator<T> iterator = set.iterator();
            private final int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iterator.hasNext();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return iterator.next();
            }
        };
    }
}