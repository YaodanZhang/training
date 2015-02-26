package com.thoughtworks.mvc.example;

import com.google.inject.AbstractModule;

public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TestAction.class);
    }
}
