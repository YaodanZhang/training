package com.thoughtworks.ioc.example.marsrover;

public interface Coordinate {
    public Coordinate plus(Coordinate offset);

    public boolean insideOf(Coordinate begin, Coordinate end);

    public Coordinate reverse();

    /**
     * @return like "x y z"
     */
    public String toStringSeparatedBySpace();

    /**
     * @return like "(x, y, z)"
     */
    public String toStringSurroundByBracketSeparatedByComma();

    /**
     * @return like "(x, y, z)"
     */
    public String toString();
}
