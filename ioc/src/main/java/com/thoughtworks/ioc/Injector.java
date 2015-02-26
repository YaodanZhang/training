package com.thoughtworks.ioc;

public class Injector {
    private ElementContainer container;

    public Injector() {
        container = new ElementContainer();
    }

    public <T> T getInstance(Class<?> clazz) {
        return container.getInstance(clazz);
    }

    public <T> T getInstance(Class<?> clazz, String annotationName) {
        return container.getInstance(clazz, annotationName);
    }

    public ElementContainer getElementContainer() {
        return container;
    }
}
