package com.thoughtworks.example;

import com.thoughtworks.ioc.AbstractModule;
import com.thoughtworks.ioc.Cortex;
import com.thoughtworks.ioc.Injector;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RoverTest {
    private Injector injector;
    private IRover rover;

    @Before
    public void setUp() throws Exception {
        injector = Cortex.createInjector(new AbstractModule() {

            @Override
            public void configure() {
                bind(IRover.class).to(Rover.class);
            }
        });
        rover = injector.getInstance(IRover.class);
    }

    @Test
    public void should_move_forward_after_calling_move() throws Exception {
        // given
        rover.setPosition(new Position(new Point(0, 0), Direction.E));

        // when
        rover.move();

        // then
        assertThat(rover.getPosition().getPoint().getX(), is(1));
        assertThat(rover.getPosition().getPoint().getY(), is(0));
        assertThat(rover.getPosition().getDirection(), is(Direction.E));
    }

    @Test
    public void should_face_east_if_north_and_turn_right() throws Exception {
        // given
        rover.setPosition(new Position(new Point(0, 0), Direction.N));

        // when
        rover.turnRight();

        // then
        assertThat(rover.getPosition().getDirection(), is(Direction.E));
    }

    @Test
    public void should_face_west_if_north_and_turn_left() throws Exception {
        // given
        rover.setPosition(new Position(new Point(0, 0), Direction.N));

        // when
        rover.turnLeft();

        // then
        assertThat(rover.getPosition().getDirection(), is(Direction.W));
    }
}
