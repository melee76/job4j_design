package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        short hands = 2;
        long weight = 500L;
        char ch = 'a';
        byte by = 7;
        float fl = 1.25F;
        double dou = 7.62;
        boolean bo = false;
        LOG.debug("User info name : {}, "
                        + "age : {}, "
                        + "hands : {}, "
                        + "weight : {}, "
                        + "ch : {}, "
                        + "by : {}, "
                        + "fl : {}, "
                        + "dou : {}, "
                        + "bo : {}",
                name, age, hands, weight, ch, by, fl, dou, bo);
    }
}