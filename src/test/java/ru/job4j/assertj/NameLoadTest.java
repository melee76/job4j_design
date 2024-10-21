package ru.job4j;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void arrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Names array is empty");
    }

    @Test
    void whenNameDoesNotContainEqualSigh() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"Alex"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: Alex does not contain the symbol '='");
    }

    @Test
    void whenNameStartsWithEqualSigh() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"=Alex"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: =Alex does not contain a key");
    }

    @Test
    void whenNameDoesNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"Alex="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: Alex= does not contain a value");
    }
}