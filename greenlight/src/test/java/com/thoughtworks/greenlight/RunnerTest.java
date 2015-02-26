package com.thoughtworks.greenlight;

import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RunnerTest {
    @Test
    public void should_run_test_methods() throws Exception {
        Runner runner = new Runner();

        TestHandler mockedHandler = mock(TestHandler.class);
        runner.runAllMethod(Arrays.asList(mockedHandler));

        verify(mockedHandler).run();
    }
}
