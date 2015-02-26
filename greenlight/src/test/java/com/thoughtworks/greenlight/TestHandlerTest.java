package com.thoughtworks.greenlight;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestHandlerTest {
    @Test
    public void should_run_method() throws Exception {
        TestHandler testHandler = new TestHandler();
        DummyTest dummyTest = new DummyTest();
        testHandler.setInstance(dummyTest);
        testHandler.setMethod(dummyTest.getClass().getDeclaredMethod("testWithoutAnnotation"));

        testHandler.run();

        assertThat(dummyTest.allMethodsHasBeenCalled(), is(true));
    }
}
