package com.thoughtworks.ioc.example.robot.singleton;

import com.thoughtworks.ioc.annotations.Inject;
import com.thoughtworks.ioc.example.robot.Robot;

public class QueenRobot implements Robot, Queen {
    @Inject
    private SingletonRobot kingRobot;


    @Override
    public Robot myKing() {
        return kingRobot;
    }
}
