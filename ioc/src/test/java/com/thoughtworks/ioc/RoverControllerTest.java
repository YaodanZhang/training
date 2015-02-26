package com.thoughtworks.ioc;

import com.google.common.collect.ImmutableList;
import com.thoughtworks.ioc.example.marsrover.*;
import com.thoughtworks.ioc.example.marsrover.twodimension.OrientationFactoryImpl;
import com.thoughtworks.ioc.example.marsrover.twodimension.PlateauImpl;
import com.thoughtworks.ioc.example.marsrover.twodimension.RoverImpl;
import com.thoughtworks.ioc.example.marsrover.twodimension.instruction.InstructionFactoryImpl;
import com.thoughtworks.ioc.example.marsrover.twodimension.instruction.MoveForwardInstruction;
import com.thoughtworks.ioc.example.marsrover.twodimension.instruction.TurnLeftInstruction;
import com.thoughtworks.ioc.example.marsrover.twodimension.instruction.TurnRightInstruction;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RoverControllerTest {

    private Injector injector;

    @Before
    public void setUp() {
        injector = Cortex.createInjector(new AbstractModule() {
            @Override
            public void configure() {
                bind(Rover.class).to(RoverImpl.class);
                bind(Plateau.class).to(PlateauImpl.class);
                bind(OrientationFactory.class).to(OrientationFactoryImpl.class);
                bind(InstructionFactory.class).to(InstructionFactoryImpl.class);
                bind(Instruction.class).to(MoveForwardInstruction.class).scope(Scope.SINGLETON)
                        .withAnnotatedName(String.valueOf(Constants.INSTRUCTION_MOVE_FORWARD));
                bind(Instruction.class).to(TurnLeftInstruction.class).scope(Scope.SINGLETON)
                        .withAnnotatedName(String.valueOf(Constants.INSTRUCTION_TURN_LEFT));
                bind(Instruction.class).to(TurnRightInstruction.class).scope(Scope.SINGLETON)
                        .withAnnotatedName(String.valueOf(Constants.INSTRUCTION_TURN_RIGHT));
                bind(InputIterator.class);
            }
        });
        InstructionFactory instructionFactory = injector.getInstance(InstructionFactory.class);
        instructionFactory.setInjector(injector);
    }

    @Test
    public void should_get_rover_status() {
        // given
        List<String> inputLeadTo55S = ImmutableList.of(
                "10 10",
                "0 0 N",
                "MMRMMMMMLMMMLL"
        );
        InputIterator inputIterator = injector.getInstance(InputIterator.class);
        inputIterator.setInput(inputLeadTo55S);
        String result = null;

        // when
        while (inputIterator.hasNext()) {
            Rover rover = inputIterator.nextRover();
            List<Instruction> instructions = inputIterator.nextInstructions();
            rover.navigate(instructions);

            result = rover.getCoordinate().toStringSeparatedBySpace() + " "
                    + rover.getOrientation().getName().toUpperCase().charAt(0);
        }

        // then
        assertThat(result, is("5 5 S"));
    }
}
