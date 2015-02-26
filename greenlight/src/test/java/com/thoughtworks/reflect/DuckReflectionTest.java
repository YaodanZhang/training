package com.thoughtworks.reflect;

import com.thoughtworks.reflect.model.Duck;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class DuckReflectionTest {

    private DuckReflection duckReflection;
    private Duck duck;

    @Before
    public void setUp() throws Exception {
        duckReflection = new DuckReflection();
        duck = duckReflection.newDuckInstance();
    }

    @Test
    public void should_create_duck_instance() throws Exception {
        assertThat(duck, instanceOf(Duck.class));
    }

    @Test
    public void should_invoke_quark_method() throws Exception {
        assertThat(duckReflection.invokeQuark(), is("I am a duck"));
    }

    @Test
    public void should_invoke_quark_method_with_argument() throws Exception {
        assertThat(duckReflection.invokeQuark("mosquito"), is("My name is mosquito"));
    }
}
