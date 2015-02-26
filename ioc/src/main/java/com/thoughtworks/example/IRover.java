package com.thoughtworks.example;

public interface IRover {
    void move();

    void turnLeft();

    void turnRight();

    Position getPosition();

    void setPosition(Position position);
}
