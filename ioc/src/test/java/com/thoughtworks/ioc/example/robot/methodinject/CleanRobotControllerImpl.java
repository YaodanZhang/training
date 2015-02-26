package com.thoughtworks.ioc.example.robot.methodinject;

import com.thoughtworks.ioc.annotations.Inject;
import com.thoughtworks.ioc.example.robot.Robot;
import com.thoughtworks.ioc.example.robot.RobotController;

public class CleanRobotControllerImpl implements RobotController {
    private Robot cleanRobot;

    @Override
    public void play() {
    }

    @Override
    public Robot getRobot() {
        return cleanRobot;
    }

    @Inject
    public void setCleanRobot(Robot cleanRobot) {
        this.cleanRobot = cleanRobot;
    }
}
