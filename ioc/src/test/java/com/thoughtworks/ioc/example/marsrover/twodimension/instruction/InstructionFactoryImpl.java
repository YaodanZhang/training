package com.thoughtworks.ioc.example.marsrover.twodimension.instruction;

import com.thoughtworks.ioc.Injector;
import com.thoughtworks.ioc.example.marsrover.Instruction;
import com.thoughtworks.ioc.example.marsrover.InstructionFactory;

import java.util.ArrayList;
import java.util.List;

public class InstructionFactoryImpl implements InstructionFactory {

    private Injector injector;

    @Override
    public Instruction getInstance(char c) {
        return injector.getInstance(Instruction.class, String.valueOf(c));
    }

    @Override
    public List<Instruction> getInstance(String s) {
        List<Instruction> instructions = new ArrayList<Instruction>();
        for (char c : s.toCharArray()) {
            instructions.add(getInstance(c));
        }
        return instructions;
    }

    @Override
    public void setInjector(Injector injector) {
        this.injector = injector;
    }
}
