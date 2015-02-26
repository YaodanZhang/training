package com.thoughtworks.ioc.example.marsrover.twodimension;

import com.thoughtworks.ioc.example.marsrover.Coordinate;
import com.thoughtworks.ioc.example.marsrover.Plateau;

public final class PlateauImpl implements Plateau {

    private Coordinate begin;
    private Coordinate end;

    @Override
    public boolean isOutOfRange(Coordinate coordinate) {
        return !coordinate.insideOf(begin, end);
    }

    @Override
    public void setRange(Coordinate begin, Coordinate end) {
        this.begin = begin;
        this.end = end;
    }

}
