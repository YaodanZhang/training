package com.thoughtworks.ioc.example.marsrover.twodimension;

import com.thoughtworks.ioc.example.marsrover.Constants;
import com.thoughtworks.ioc.example.marsrover.Orientation;
import com.thoughtworks.ioc.example.marsrover.OrientationFactory;

import static com.thoughtworks.ioc.example.marsrover.twodimension.OrientationImpl.*;

public class OrientationFactoryImpl implements OrientationFactory {

    @Override
    public Orientation getOrientation(String s) {
        if (s.equals(Constants.ORIENTATION_WEST)) {
            return WEST;
        } else if (s.equals(Constants.ORIENTATION_NORTH)) {
            return NORTH;
        } else if (s.equals(Constants.ORIENTATION_SOUTH)) {
            return SOUTH;
        } else if (s.equals(Constants.ORIENTATION_EAST)) {
            return EAST;
        } else {
            throw new IllegalArgumentException(
                    "Wrong parameter letter for orientation, only N, W, S, E can be accepted!");
        }
    }
}
