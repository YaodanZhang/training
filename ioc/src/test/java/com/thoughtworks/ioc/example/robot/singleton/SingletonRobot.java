package com.thoughtworks.ioc.example.robot.singleton;

import com.thoughtworks.ioc.annotations.ImplementedBy;
import com.thoughtworks.ioc.annotations.Singleton;
import com.thoughtworks.ioc.example.robot.Robot;

@ImplementedBy(KingRobot.class)
@Singleton
public interface SingletonRobot extends Robot {
}
