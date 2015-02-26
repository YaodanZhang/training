package com.thoughtworks.ioc;

import com.thoughtworks.ioc.example.robot.RobotController;
import com.thoughtworks.ioc.example.robot.creation.RobotControllerImpl;
import com.thoughtworks.ioc.example.robot.singleton.SingletonRobot;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CortexTest {

    private Injector injector;

    @Before
    public void setUp() throws Exception {
        injector = Cortex.createInjector(new AbstractModule() {

            @Override
            public void configure() {
                bind(RobotController.class).to(RobotControllerImpl.class);
                bind(SingletonRobot.class);
            }
        });
    }

    @Test
    public void should_create_injector_with_module() throws Exception {
        // when
        RobotController controller = injector.getInstance(RobotController.class);

        // then
        assertNotNull(controller);
    }

    @Test
    public void should_return_same_instance_if_singleton_annotation() throws Exception {
        // when
        SingletonRobot kingRobot1 = injector.getInstance(SingletonRobot.class);
        SingletonRobot kingRobot2 = injector.getInstance(SingletonRobot.class);

        // then
        assertThat(kingRobot1, Matchers.is(kingRobot2));
    }
}
