package com.thoughtworks.ioc.example.marsrover;

public interface Orientation {

    public Orientation turnLeft();

    public Orientation turnRight();

    public Coordinate getForwardOffset();

    public Coordinate getBackwardOffset();

    public String getName();

    public String toString();

}
