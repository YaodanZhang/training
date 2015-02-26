package com.thoughtworks.greenlight;

public class DummyTest {
    private boolean methodWithoutAnnotationCalled;

    @Case
    public void testWithoutAnnotation() {
        this.methodWithoutAnnotationCalled = true;
    }

    boolean allMethodsHasBeenCalled() {
        return methodWithoutAnnotationCalled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DummyTest)) return false;

        DummyTest dummyTest = (DummyTest) o;

        if (methodWithoutAnnotationCalled != dummyTest.methodWithoutAnnotationCalled) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (methodWithoutAnnotationCalled ? 1 : 0);
    }
}
