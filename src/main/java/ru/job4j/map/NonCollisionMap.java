package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int findIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean check(K key1, K key2) {
        return Objects.hashCode(key1) == Objects.hashCode(key2) && Objects.equals(key1, key2);
    }

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int bucket = findIndex(key);
        if (table[bucket] == null) {
            table[bucket] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newMap = new MapEntry[capacity];
        for (MapEntry<K, V> map : table) {
            if (map != null) {
                int bucket = findIndex(map.key);
                newMap[bucket] = map;
            }
        }
        table = newMap;
    }

    @Override
    public V get(K key) {
        V value = null;
        int bucket = findIndex(key);
        MapEntry<K, V> map = table[bucket];
        if (map != null && check(map.key, key)) {
            value = map.value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int bucket = findIndex(key);
        MapEntry<K, V> map = table[bucket];
        if (map != null && check(map.key, key)) {
            table[bucket] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int lastMod = modCount;
        return new Iterator<>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (modCount != lastMod) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length
                        && table[cursor] == null) {
                    cursor++;
                }
                return cursor < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}