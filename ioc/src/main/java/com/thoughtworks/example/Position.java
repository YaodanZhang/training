package com.thoughtworks.example;

public class Position {
    private Point point;
    private Direction direction;

    public Position(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
