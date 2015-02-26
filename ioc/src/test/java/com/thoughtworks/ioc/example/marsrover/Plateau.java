package com.thoughtworks.ioc.example.marsrover;

import com.thoughtworks.ioc.annotations.Singleton;

@Singleton
public interface Plateau {
    public boolean isOutOfRange(Coordinate coordinate);

    public void setRange(Coordinate begin, Coordinate end);
}
