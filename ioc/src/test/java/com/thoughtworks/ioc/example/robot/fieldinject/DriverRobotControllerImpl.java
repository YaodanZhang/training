package com.thoughtworks.ioc.example.robot.fieldinject;

import com.thoughtworks.ioc.annotations.Inject;
import com.thoughtworks.ioc.example.robot.Robot;
import com.thoughtworks.ioc.example.robot.RobotController;

public class DriverRobotControllerImpl implements RobotController {
    @Inject
    private Robot robot;

    @Override
    public void play() {
    }

    @Override
    public Robot getRobot() {
        return robot;
    }
}
