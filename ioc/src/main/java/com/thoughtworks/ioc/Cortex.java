package com.thoughtworks.ioc;

import java.util.List;

public class Cortex {

    public static Injector createInjector(AbstractModule... modules) {
        Injector injector = new Injector();
        for (AbstractModule module : modules) {
            module.configure();

            List<ElementBuilder> elementBuilders = module.getElementBuilders();
            for (ElementBuilder elementBuilder : elementBuilders) {
                injector.getElementContainer().add(elementBuilder.build());
            }
        }
        return injector;
    }
}
