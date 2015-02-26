package com.thoughtworks.ioc.example.marsrover;

import com.thoughtworks.ioc.Injector;
import com.thoughtworks.ioc.annotations.Singleton;

import java.util.List;

@Singleton
public interface InstructionFactory {

    public Instruction getInstance(char c);

    public List<Instruction> getInstance(String s);

    public void setInjector(Injector injector);
}
