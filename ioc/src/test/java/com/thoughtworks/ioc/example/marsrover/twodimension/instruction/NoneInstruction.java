package com.thoughtworks.ioc.example.marsrover.twodimension.instruction;

import com.thoughtworks.ioc.example.marsrover.Instruction;
import com.thoughtworks.ioc.example.marsrover.Rover;

public class NoneInstruction implements Instruction {

    private String instruction = "No Instruction";

    @Override
    public Rover action(Rover rover) {
        return rover;
    }

    @Override
    public Rover undo(Rover rover) {
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
