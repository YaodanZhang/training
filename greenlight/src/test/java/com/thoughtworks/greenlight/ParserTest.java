package com.thoughtworks.greenlight;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

public class ParserTest {
    @Test
    public void should_get_all_methods_of_dummy() throws Exception {
        Parser parser = new Parser("com.thoughtworks.greenlight.DummyTest");

        List<TestHandler> allMethodsHandlers = parser.getTestMethodsHandlers();

        TestHandler testHandler = new TestHandler();
        testHandler.setInstance(new DummyTest());
        testHandler.setMethod(DummyTest.class.getMethod("testWithoutAnnotation"));

        assertThat(new TestHandler[]{testHandler}, arrayContainingInAnyOrder(allMethodsHandlers.toArray()));
    }
}
