package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Pupil pupil = new Pupil(false, 7, new School("666"),
                new String[] {"Father", "Mother"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(pupil));

        final String pupilJson =
                "{"
                        + "\"sex\":false,"
                        + "\"form\":7,"
                        + "\"school\":"
                        + "{"
                        + "\"number\":\"666\""
                        + "},"
                        + "\"parents\":"
                        + "[\"Alex\",\"Nina\"]"
                        + "}";
        final Pupil personMod = gson.fromJson(pupilJson, Pupil.class);
        System.out.println(personMod);
    }
}
