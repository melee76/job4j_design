package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemovedByFilter() {
        input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> filter = i -> i <= 2;
        ListUtils.removeIf(input, filter);
        List<Integer> result = Arrays.asList(3, 4);
        assertThat(input).isEqualTo(result);
    }

    @Test
    void whenReplacedByFilter() {
        input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> filter = i -> i <= 2;
        ListUtils.replaceIf(input, filter, 9);
        List<Integer> result = Arrays.asList(9, 9, 9, 3, 4);
        assertThat(input).isEqualTo(result);
    }

    @Test
    void whenRemovedAll() {
        input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> second = Arrays.asList(2, 3);
        ListUtils.removeAll(input, second);
        List<Integer> result = Arrays.asList(0, 1, 4);
        assertThat(input).isEqualTo(result);
    }
}