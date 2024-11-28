package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) throws IllegalArgumentException {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) throws IllegalArgumentException {
        for (String ar : args) {
            validateArgument(ar);
            if (ar.startsWith("-")) {
                ar = ar.substring(1);
            }
            String[] parts = ar.split("=", 2);
            if (parts.length < 2) {
                throw new IllegalArgumentException("The argument has wrong template");
            }
            String key = parts[0];
            String value = parts[1];
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void validateArgument(String arg) {
        String[] parts = arg.split("=", 2);
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not start with a '-' character");
        }
        if (arg.startsWith("-=")) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
        }
        if (arg.endsWith("=") && parts[1].isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain an equal sign");
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}