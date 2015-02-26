package com.thoughtworks.ioc.example.marsrover;

public interface Instruction {

    public Rover action(Rover rover);

    public Rover undo(Rover rover);

    public String getInstructionName();
}
