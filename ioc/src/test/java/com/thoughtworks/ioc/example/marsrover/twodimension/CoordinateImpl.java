package com.thoughtworks.ioc.example.marsrover.twodimension;

import com.google.common.base.Objects;
import com.thoughtworks.ioc.example.marsrover.Coordinate;

public final class CoordinateImpl implements Coordinate {

    private final int x;
    private final int y;

    public final static Coordinate MIN_COORDINATE;

    static {
        MIN_COORDINATE = new CoordinateImpl(0, 0);
    }

    public CoordinateImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Coordinate plus(Coordinate o) {
        CoordinateImpl offset = (CoordinateImpl) o;
        return new CoordinateImpl(x + offset.x, y + offset.y);
    }

    @Override
    public boolean insideOf(Coordinate b, Coordinate e) {
        CoordinateImpl begin = (CoordinateImpl) b;
        CoordinateImpl end = (CoordinateImpl) e;
        return !(x < begin.x || y < begin.y || x > end.x || y > end.y);
    }

    @Override
    public Coordinate reverse() {
        return new CoordinateImpl(0 - x, 0 - y);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof CoordinateImpl) {
            CoordinateImpl coordinate = (CoordinateImpl) o;
            return coordinate.x == this.x && coordinate.y == this.y;
        }
        return false;
    }


    @Override
    public String toStringSeparatedBySpace() {
        return x + " " + y;
    }

    @Override
    public String toStringSurroundByBracketSeparatedByComma() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public String toString() {
        return toStringSurroundByBracketSeparatedByComma();
    }
}
