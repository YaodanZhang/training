package com.thoughtworks.example;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DirectionTest {
    @Test
    public void test_turning_right() throws Exception {
        // given
        Direction north = Direction.N;

        // when
        Direction east = north.turnRight();

        // then
        assertThat(east, is(Direction.E));

        // when
        Direction south = east.turnRight();

        // then
        assertThat(south, is(Direction.S));

        // when
        Direction west = south.turnRight();

        // then
        assertThat(west, is(Direction.W));

        // when
        Direction east2 = west.turnRight();

        // then
        assertThat(east2, is(Direction.N));
    }

    @Test
    public void test_turn_left() throws Exception {
        // given
        Direction north = Direction.N;

        // when
        Direction west = north.turnLeft();

        // then
        assertThat(west, is(Direction.W));

        // when
        Direction south = west.turnLeft();

        // then
        assertThat(south, is(Direction.S));
    }
}
