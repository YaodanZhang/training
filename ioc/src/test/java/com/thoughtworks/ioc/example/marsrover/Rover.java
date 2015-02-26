package com.thoughtworks.ioc.example.marsrover;

import java.util.List;

public interface Rover {

    public Rover navigate(List<Instruction> instructionList);

    public Rover navigate(Instruction instruction);

    // getters & setters
    public Orientation getOrientation();

    public void setOrientation(Orientation orientation);

    public Coordinate getCoordinate();

    public void setCoordinate(Coordinate coordinate);

    public Plateau getPlateau();

    public void setPlateau(Plateau plateau);
}
