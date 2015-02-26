package com.thoughtworks.ioc.example.marsrover.twodimension;

import com.thoughtworks.ioc.annotations.Inject;
import com.thoughtworks.ioc.example.marsrover.*;

import java.util.List;

public class StandardInput implements InputIterator {

    private static final int LINE_OF_PLATEAU_COORDINATES = 0;

    private static final String SPLIT_OF_INPUT = "\\s+";

    private List<String> lineList;

    private int position;

    private Plateau plateau;
    private Rover rover;

    @Inject
    private OrientationFactory orientationFactory;

    private InstructionFactory instructionFactory;

    @Inject
    public StandardInput(Plateau plateau, Rover rover) {
        this.plateau = plateau;
        this.rover = rover;
        position = 1;
    }

    public final Rover nextRover() {
        String[] lineParas = lineList.get(position).split(SPLIT_OF_INPUT);
        Coordinate coordinate = null;

        coordinate = new CoordinateImpl(Integer.valueOf(lineParas[0]),
                Integer.valueOf(lineParas[1]));
        rover.setCoordinate(coordinate);
        rover.setOrientation(orientationFactory.getOrientation(lineParas[2]));
        rover.setPlateau(plateau);

        position += 2;
        return rover;
    }

    @Override
    public final List<Instruction> nextInstructions() {
        List<Instruction> instructions = instructionFactory
                .getInstance(lineList.get(position - 1));

        return instructions;
    }

    @Override
    public final boolean hasNext() {
        return position < lineList.size();
    }

    @Override
    public void setInput(List<String> input) {
        this.lineList = input;
        readPlateau();
    }

    private void readPlateau() {
        String[] upperRight = lineList.get(LINE_OF_PLATEAU_COORDINATES).split(
                SPLIT_OF_INPUT);
        plateau.setRange(CoordinateImpl.MIN_COORDINATE,
                new CoordinateImpl(Integer.valueOf(upperRight[0]), Integer.valueOf(upperRight[1])));
    }

    @Inject
    public void setInstructionFactory(InstructionFactory instructionFactory) {
        this.instructionFactory = instructionFactory;
    }

}
