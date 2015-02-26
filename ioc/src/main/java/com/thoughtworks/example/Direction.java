package com.thoughtworks.example;

public enum Direction {
    E, S, W, N;

    public Direction turnRight() {
        return values()[(ordinal() + 1) % 4];
    }

    public Direction turnLeft() {
        return values()[(ordinal() + 3) % 4];
    }
}
