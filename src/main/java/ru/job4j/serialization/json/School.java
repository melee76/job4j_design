package ru.job4j.serialization.json;

public class School {
    private final String number;

    public School(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "School{"
                + "number='" + number + '\''
                + '}';
    }
}