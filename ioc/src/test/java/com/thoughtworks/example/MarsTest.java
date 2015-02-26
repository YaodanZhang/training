package com.thoughtworks.example;

import com.thoughtworks.ioc.AbstractModule;
import com.thoughtworks.ioc.Cortex;
import com.thoughtworks.ioc.Injector;
import com.thoughtworks.ioc.Scope;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MarsTest {

    private Injector injector;

    @Before
    public void setUp() throws Exception {
        injector = Cortex.createInjector(new AbstractModule() {

            @Override
            public void configure() {
                bind(IMars.class).to(Mars.class).scope(Scope.SINGLETON);
            }
        });
    }

    @Test
    public void should_return_correct_width() throws Exception {
        // given
        IMars mars = injector.getInstance(IMars.class);

        // when
        mars.setRange(5, 5);

        // then
        assertThat(mars.getWidth(), is(5));
        assertThat(mars.getHeight(), is(5));
    }
}
