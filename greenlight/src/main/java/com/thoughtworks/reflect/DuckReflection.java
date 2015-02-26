package com.thoughtworks.reflect;

import com.thoughtworks.reflect.model.Duck;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DuckReflection {
    public Duck newDuckInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return getDuckClass().newInstance();
    }

    public String invokeQuark() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<Duck> duckClazz = getDuckClass();
        Method quark = duckClazz.getDeclaredMethod("quark");
        return (String) quark.invoke(duckClazz.newInstance());
    }

    public String invokeQuark(String mosquito) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<Duck> duckClazz = getDuckClass();
        Method quark = duckClazz.getDeclaredMethod("quark", String.class);
        return (String) quark.invoke(duckClazz.newInstance(), mosquito);
    }

    private Class<Duck> getDuckClass() throws ClassNotFoundException {
        return (Class<Duck>) Class.forName("com.thoughtworks.reflect.model.Duck");
    }
}
