package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int in = 0;
    private int out = 0;

    public T poll() {
        if (in == 0 && out == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (out == 0) {
            while (in > 0) {
                output.push(input.pop());
                in--;
                out++;
            }
        }
        out--;
        return output.pop();
}

    public void push(T value) {
        input.push(value);
        in++;
    }
}