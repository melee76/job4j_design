package ru.job4j.serialization.json;

import java.util.Arrays;

public class Pupil {
    private final boolean sex;
    private final int form;
    private final School school;
    private final String[] parents;

    public Pupil(boolean sex, int form, School school, String[] parents) {
        this.sex = sex;
        this.form = form;
        this.school = school;
        this.parents = parents;
}

    @Override
    public String toString() {
        return "Pupil{"
                + "sex=" + sex
                + ", form=" + form
                + ", school=" + school
                + ", parents=" + Arrays.toString(parents)
                + '}';
    }
}
