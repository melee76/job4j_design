package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        String[] filter = argsName.get("filter").split(",");
        List<String> names = new ArrayList<>();
        List<Integer> filterIndexes = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(argsName.get("path")));
             PrintWriter print = !"stdout".equals(argsName.get("out"))
                     ? new PrintWriter(new FileWriter(argsName.get("out")))
                     : new PrintWriter(System.out)) {
            String first = in.readLine();
            if (first != null) {
                String[] columns = first.split(argsName.get("delimiter"));
                Collections.addAll(names, columns);
            }
            for (String name : filter) {
                int index = names.indexOf(name);
                if (index != -1) {
                    filterIndexes.add(index);
                }
            }
            StringBuilder headerLine = new StringBuilder();
            for (String name : filter) {
                headerLine.append(name).append(argsName.get("delimiter"));
            }
            if (headerLine.length() > 0) {
                headerLine.setLength(headerLine.length() - 1);
            }
            print.println(headerLine);
            String line;
            while ((line = in.readLine()) != null) {
                Scanner scanner = new Scanner(line).useDelimiter(argsName.get("delimiter"));
                StringBuilder outputLine = new StringBuilder();
                List<String> values = new ArrayList<>();
                while (scanner.hasNext()) {
                    values.add(scanner.next());
                }
                for (int idx : filterIndexes) {
                    if (idx < values.size()) {
                        outputLine.append(values.get(idx)).append(argsName.get("delimiter"));
                    }
                }
                if (outputLine.length() > 0) {
                    outputLine.setLength(outputLine.length() - 1);
                }
                print.println(outputLine);
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validate(ArgsName argsName) {
        File file = new File(argsName.get("path"));
        if (!Files.isRegularFile(file.toPath())) {
            throw new IllegalArgumentException(String.format("Not a file %s", file.toPath()));
        }
        String outFile = argsName.get("out");
        if (!("stdout".equals(outFile) || outFile.endsWith(".csv"))) {
            throw new IllegalArgumentException("You need to check the 3rd argument");
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong numbers of the arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}