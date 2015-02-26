package com.thoughtworks.ioc.example.robot.namedinject;

import com.thoughtworks.ioc.annotations.Inject;
import com.thoughtworks.ioc.annotations.Named;
import com.thoughtworks.ioc.example.robot.Robot;
import com.thoughtworks.ioc.example.robot.RobotController;

public class DanceRobotControllerImpl implements RobotController {
    @Inject
    @Named("dancer")
    private Robot danceRobot;

    @Override
    public void play() {
    }

    @Override
    public Robot getRobot() {
        return danceRobot;
    }
}
