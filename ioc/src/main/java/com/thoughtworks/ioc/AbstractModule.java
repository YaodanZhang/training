package com.thoughtworks.ioc;

import com.google.common.collect.Lists;

import java.util.List;

public abstract class AbstractModule {
    private List<ElementBuilder> elementBuilders = Lists.newArrayList();

    public abstract void configure();

    public ElementBuilder bind(Class<?> clazz) {
        ElementBuilder elementBuilder = new ElementBuilder(clazz);
        elementBuilders.add(elementBuilder);
        return elementBuilder;
    }

    List<ElementBuilder> getElementBuilders() {
        return elementBuilders;
    }
}
