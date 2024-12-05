package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "school")
@XmlAccessorType(XmlAccessType.FIELD)
public class School {
    @XmlAttribute
    private String number;

    public School() {
    }

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