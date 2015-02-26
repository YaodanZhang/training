package com.thoughtworks.greenlight;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Runner {
    public void runAllMethod(List<TestHandler> tests) throws InvocationTargetException, IllegalAccessException {
        for (TestHandler testHandler : tests) {
            testHandler.run();
        }
    }
}
