package com.thoughtworks.ioc.example.marsrover;

import com.thoughtworks.ioc.annotations.ImplementedBy;
import com.thoughtworks.ioc.example.marsrover.twodimension.StandardInput;

import java.util.List;

@ImplementedBy(StandardInput.class)
public interface InputIterator {

    public Rover nextRover();

    public List<Instruction> nextInstructions();

    public boolean hasNext();

    public void setInput(List<String> input);
}
