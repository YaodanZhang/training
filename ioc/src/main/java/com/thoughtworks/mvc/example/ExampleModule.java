package com.thoughtworks.mvc.example;

import com.google.inject.AbstractModule;

public class ExampleModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MyAction.class).asEagerSingleton();
    }
}
