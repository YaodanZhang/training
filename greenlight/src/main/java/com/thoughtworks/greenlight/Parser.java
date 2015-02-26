package com.thoughtworks.greenlight;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Parser {
    private final Class<?> clazz;
    private Object instance;
    private List<TestHandler> testMethodsHandlers;

    public Parser(String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        clazz = getClass(className);
        instance = createInstance(clazz);
        parseAllMethodsHandlers();
    }

    private void parseAllMethodsHandlers() {
        testMethodsHandlers = newArrayList();
        for (Method method : getTestMethods()) {
            TestHandler testHandler = new TestHandler();
            testHandler.setInstance(instance);
            testHandler.setMethod(method);

            testMethodsHandlers.add(testHandler);
        }
    }

    private Iterable<Method> getTestMethods() {
        return Iterables.filter(Arrays.asList(clazz.getDeclaredMethods()), new Predicate<Method>() {
            @Override
            public boolean apply(Method method) {
                return method.isAnnotationPresent(Case.class);
            }
        });
    }

    private Object createInstance(Class<?> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    private Class<?> getClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    public List<TestHandler> getTestMethodsHandlers() {
        return testMethodsHandlers;
    }
}
