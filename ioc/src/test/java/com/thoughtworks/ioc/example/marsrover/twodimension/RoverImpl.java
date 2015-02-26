package com.thoughtworks.ioc.example.marsrover.twodimension;

import com.thoughtworks.ioc.example.marsrover.*;

import java.util.List;

public class RoverImpl implements Rover {

    private Coordinate coordinate;
    private Orientation orientation;
    private Plateau plateau;

    @Override
    public Rover navigate(List<Instruction> instructionList) {
        for (Instruction instruction : instructionList) {
            _navigateOneInstruction(instruction);
        }
        return this;
    }

    @Override
    public Rover navigate(Instruction instruction) {
        _navigateOneInstruction(instruction);
        return this;
    }

    private void _navigateOneInstruction(Instruction instruction) {
        instruction.action(this);
        if (plateau.isOutOfRange(coordinate)) {
            instruction.undo(this);
            throw new IllegalArgumentException("out of range!");
        }
    }

    // getters & setters
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public Plateau getPlateau() {
        return plateau;
    }

    @Override
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }
}
