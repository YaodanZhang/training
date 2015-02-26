package com.thoughtworks.ioc;

import com.thoughtworks.ioc.annotations.Singleton;

public class ElementBuilder {
    private AbstractElement element;

    public ElementBuilder(Class<?> clazz) {
        element = new PrototypeElement(clazz);
        if (clazz.isAnnotationPresent(Singleton.class)) {
            transformToSingleton();
        }
    }

    public ElementBuilder to(Class<?> clazz) {
        element.setValueClass(clazz);
        return this;
    }

    AbstractElement build() {
        return element;
    }

    public ElementBuilder withAnnotatedName(String annotatedName) {
        element.setAnnotatedName(annotatedName);
        return this;
    }

    public ElementBuilder scope(Scope scope) {
        if (scope == Scope.SINGLETON) {
            transformToSingleton();
        }
        return this;
    }

    private void transformToSingleton() {
        element = new SingletonElement(element);
    }
}
