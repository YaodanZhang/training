package com.thoughtworks.ioc.example.marsrover.twodimension.instruction;

import com.thoughtworks.ioc.example.marsrover.Coordinate;
import com.thoughtworks.ioc.example.marsrover.Instruction;
import com.thoughtworks.ioc.example.marsrover.Rover;

public class MoveForwardInstruction implements Instruction {

    private String instruction = "Move Forward";

    @Override
    public Rover action(Rover rover) {
        Coordinate coordinate = rover.getCoordinate();
        rover.setCoordinate(coordinate.plus(rover.getOrientation()
                .getForwardOffset()));
        return rover;
    }

    @Override
    public Rover undo(Rover rover) {
        Coordinate coordinate = rover.getCoordinate();
        rover.setCoordinate(coordinate.plus(rover.getOrientation()
                .getBackwardOffset()));
        return rover;
    }

    @Override
    public String getInstructionName() {
        return instruction;
    }

    @Override
    public String toString() {
        return instruction;
    }
}
