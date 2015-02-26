package com.thoughtworks.ioc.example.marsrover.twodimension.instruction;

import com.thoughtworks.ioc.example.marsrover.Instruction;
import com.thoughtworks.ioc.example.marsrover.Rover;

public class TurnLeftInstruction implements Instruction {

    private String instruction = "Turn Left";

    @Override
    public Rover action(Rover rover) {
        rover.setOrientation(rover.getOrientation().turnLeft());
        return rover;
    }

    @Override
    public Rover undo(Rover rover) {
        rover.setOrientation(rover.getOrientation().turnRight());
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
