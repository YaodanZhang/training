package com.thoughtworks.ioc;

import com.thoughtworks.ioc.example.robot.Robot;
import com.thoughtworks.ioc.example.robot.RobotController;
import com.thoughtworks.ioc.example.robot.constructorinject.CookRobot;
import com.thoughtworks.ioc.example.robot.constructorinject.CookRobotControllerImpl;
import com.thoughtworks.ioc.example.robot.creation.RobotControllerImpl;
import com.thoughtworks.ioc.example.robot.fieldinject.DriverRobot;
import com.thoughtworks.ioc.example.robot.fieldinject.DriverRobotControllerImpl;
import com.thoughtworks.ioc.example.robot.methodinject.CleanRobot;
import com.thoughtworks.ioc.example.robot.methodinject.CleanRobotControllerImpl;
import com.thoughtworks.ioc.example.robot.namedinject.DanceRobot;
import com.thoughtworks.ioc.example.robot.namedinject.DanceRobotControllerImpl;
import com.thoughtworks.ioc.example.robot.singleton.KingRobot;
import com.thoughtworks.ioc.example.robot.singleton.Queen;
import com.thoughtworks.ioc.example.robot.singleton.QueenRobot;
import com.thoughtworks.ioc.example.robot.singleton.SingletonRobot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ElementContainerTest {

    private ElementContainer elementContainer;

    @Before
    public void setUp() {
        elementContainer = new ElementContainer();
    }

    @Test
    public void should_create_instance() {
        // given
        elementContainer.add(buildRobotControllerElement(RobotControllerImpl.class));

        // when
        RobotController controller = elementContainer.getInstance(RobotController.class);

        // then
        assertTrue(controller instanceof RobotControllerImpl);
    }

    @Test
    public void should_create_instance_with_field_inject() {
        // given
        elementContainer.add(buildRobotControllerElement(DriverRobotControllerImpl.class));
        elementContainer.add(new ElementBuilder(Robot.class).to(DriverRobot.class).build());

        // when
        RobotController controller = elementContainer.getInstance(RobotController.class);

        // then
        assertTrue(controller.getRobot() instanceof DriverRobot);
    }

    @Test
    public void should_create_instance_with_constructor_inject() {
        // given
        elementContainer.add(buildRobotControllerElement(CookRobotControllerImpl.class));
        elementContainer.add(new ElementBuilder(Robot.class).to(CookRobot.class).build());

        // when
        RobotController controller = elementContainer.getInstance(RobotController.class);

        // then
        assertTrue(controller.getRobot() instanceof CookRobot);
    }

    @Test
    public void should_create_instance_with_method_inject() {
        // given
        elementContainer.add(buildRobotControllerElement(CleanRobotControllerImpl.class));
        elementContainer.add(new ElementBuilder(Robot.class).to(CleanRobot.class).build());

        // when
        RobotController controller = elementContainer.getInstance(RobotController.class);

        // then
        assertTrue(controller.getRobot() instanceof CleanRobot);
    }

    @Test
    public void should_create_instance_with_named_field_inject() {
        // given
        elementContainer.add(buildRobotControllerElement(DanceRobotControllerImpl.class));
        elementContainer.add(new ElementBuilder(Robot.class).to(DanceRobot.class).withAnnotatedName("dancer").build());

        // when
        RobotController controller = elementContainer.getInstance(RobotController.class);

        // then
        assertTrue(controller.getRobot() instanceof DanceRobot);
    }

    @Test
    public void should_return_same_instance_if_singleton() throws Exception {
        // given
        elementContainer.add(new ElementBuilder(RobotController.class)
                .to(RobotControllerImpl.class)
                .scope(Scope.SINGLETON).build());

        //when
        RobotController controller1 = elementContainer.getInstance(RobotController.class);
        RobotController controller2 = elementContainer.getInstance(RobotController.class);

        // then
        assertTrue(controller1 == controller2);
    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_exception_if_two_same_bindings() throws Exception {
        // when
        elementContainer.add(
                new ElementBuilder(RobotController.class).to(RobotControllerImpl.class).build());
        elementContainer.add(
                new ElementBuilder(RobotController.class).to(RobotControllerImpl.class).build());

    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_exception_if_two_same_bindings2() throws Exception {
        // when
        elementContainer.add(
                new ElementBuilder(RobotController.class)
                        .to(RobotControllerImpl.class)
                        .withAnnotatedName("Robot")
                        .build());
        elementContainer.add(
                new ElementBuilder(RobotController.class)
                        .to(RobotControllerImpl.class)
                        .withAnnotatedName("Robot")
                        .build());

    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_exception_if_two_same_bindings3() throws Exception {
        // when
        elementContainer.add(
                new ElementBuilder(RobotController.class)
                        .to(RobotControllerImpl.class)
                        .withAnnotatedName("Robot")
                        .build());
        elementContainer.add(
                new ElementBuilder(RobotController.class)
                        .to(RobotControllerImpl.class)
                        .withAnnotatedName("Robot")
                        .scope(Scope.SINGLETON)
                        .build());
    }

    @Test
    public void should_inject_singleton_instance() throws Exception {
        // given
        elementContainer.add(
                new ElementBuilder(Queen.class)
                        .to(QueenRobot.class)
                        .build()
        );
        elementContainer.add(
                new ElementBuilder(SingletonRobot.class)
                        .to(KingRobot.class)
                        .scope(Scope.SINGLETON)
                        .build()
        );

        // when
        Queen queenRobot = elementContainer.getInstance(Queen.class);
        SingletonRobot kingRobot = elementContainer.getInstance(SingletonRobot.class);

        // then
        assertTrue(kingRobot == queenRobot.myKing());
    }

    private AbstractElement buildRobotControllerElement(Class<?> targetClass) {
        return new ElementBuilder(RobotController.class).to(targetClass).build();
    }
}
