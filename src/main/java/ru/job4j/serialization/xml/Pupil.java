package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "pupil")
public class Pupil {
    @XmlAttribute
    private boolean sex;
    @XmlAttribute
    private int form;
    private School school;
    @XmlElementWrapper(name = "parents")
    @XmlElement(name = "parent")
    private String[] parentses;

    public Pupil() {
    }

    public Pupil(boolean sex, int form, School school, String... parentses) {
        this.sex = sex;
        this.form = form;
        this.school = school;
        this.parentses = parentses;
    }

    @Override
    public String toString() {
        return "Pupil{"
                + "sex=" + sex
                + ", form=" + form
                + ", school=" + school
                + ", parents=" + Arrays.toString(parentses)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Pupil pupil = new Pupil(false, 7, new School("666"), "Father", "Mother");

        JAXBContext context = JAXBContext.newInstance(Pupil.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(pupil, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
