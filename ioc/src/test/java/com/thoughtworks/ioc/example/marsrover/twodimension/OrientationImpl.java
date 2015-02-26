package com.thoughtworks.ioc.example.marsrover.twodimension;

import com.thoughtworks.ioc.example.marsrover.Coordinate;
import com.thoughtworks.ioc.example.marsrover.Orientation;

public enum OrientationImpl implements Orientation {

    NORTH("North", new CoordinateImpl(0, 1)),

    EAST("East", new CoordinateImpl(1, 0)),

    SOUTH("South", new CoordinateImpl(0, -1)),

    WEST("West", new CoordinateImpl(-1, 0));

    private String name;
    private Coordinate forwardOffset;

    private static OrientationImpl[] orientationImpls;

    static {
        orientationImpls = OrientationImpl.values();
    }

    private OrientationImpl(String name, Coordinate forwardOffset) {
        this.name = name;
        this.forwardOffset = forwardOffset;
    }

    @Override
    public Orientation turnLeft() {
        return orientationImpls[(this.ordinal() - 1 + orientationImpls.length)
                % orientationImpls.length];
    }

    @Override
    public Orientation turnRight() {
        return orientationImpls[(this.ordinal() + 1) % orientationImpls.length];
    }

    @Override
    public Coordinate getForwardOffset() {
        return this.forwardOffset;
    }

    @Override
    public Coordinate getBackwardOffset() {
        return this.forwardOffset.reverse();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
