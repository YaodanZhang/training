package com.thoughtworks.ioc.example.robot.constructorinject;

import com.thoughtworks.ioc.annotations.Inject;
import com.thoughtworks.ioc.example.robot.Robot;
import com.thoughtworks.ioc.example.robot.RobotController;

public class CookRobotControllerImpl implements RobotController {

    private Robot cookRobot;

    @Inject
    public CookRobotControllerImpl(Robot cookRobot) {
        this.cookRobot = cookRobot;
    }

    @Override
    public void play() {
    }

    @Override
    public Robot getRobot() {
        return cookRobot;
    }
}
