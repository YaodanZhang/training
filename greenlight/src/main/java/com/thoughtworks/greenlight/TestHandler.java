package com.thoughtworks.greenlight;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestHandler {
    private Object instance;
    private Method method;

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void run() throws InvocationTargetException, IllegalAccessException {
        method.invoke(instance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestHandler)) return false;

        TestHandler that = (TestHandler) o;

        if (instance != null ? !instance.equals(that.instance) : that.instance != null) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instance != null ? instance.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        return result;
    }
}
