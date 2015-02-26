package com.thoughtworks.example;

public class Rover implements IRover {
    private Position position;

    @Override
    public void move() {
        switch (position.getDirection()) {
            case E:
                moveEast();
                break;
            case S:
                moveSouth();
                break;
            case W:
                moveWest();
                break;
            case N:
                moveNorth();
                break;
        }
    }

    private void moveNorth() {
        position.setPoint(new Point(position.getPoint().getX(),
                position.getPoint().getY() + 1));
    }

    private void moveWest() {
        position.setPoint(new Point(position.getPoint().getX() - 1,
                position.getPoint().getY()));
    }

    private void moveSouth() {
        position.setPoint(new Point(position.getPoint().getX(),
                position.getPoint().getY() - 1));
    }

    private void moveEast() {
        position.setPoint(new Point(position.getPoint().getX() + 1,
                position.getPoint().getY()));
    }

    @Override
    public void turnLeft() {
        position.setDirection(position.getDirection().turnLeft());
    }

    @Override
    public void turnRight() {
        position.setDirection(position.getDirection().turnRight());
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}
