package com.thoughtworks.mvc.container;

import com.google.inject.AbstractModule;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ModuleClassLoaderTest {

    private ModuleClassLoader moduleClassLoader;

    @Before
    public void setUp() throws Exception {
        moduleClassLoader = new ModuleClassLoader();
    }

    @Test
    public void should_be_able_to_load_module() throws Exception {
         // given
        String modulePath = "com.thoughtworks.mvc.example.ExampleModule";

        // when
        AbstractModule module = moduleClassLoader.load(modulePath);

        // then
        assertThat(module, notNullValue());
    }
}
