package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty();
    }

    @Test
    void howManyVertexes() {
        Box box = new Box(4, 5);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(4)
                .isGreaterThan(0);
    }

    @Test
    void checkBoolean() {
        Box box = new Box(3, 3);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse()
                .isNotNull();
    }

    @Test
    void isGetArea() {
        Box box = new Box(8, 2);
        double e = box.getArea();
        assertThat(e).isPositive()
                .isLessThan(25);
    }
}