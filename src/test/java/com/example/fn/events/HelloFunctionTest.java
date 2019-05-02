package com.example.fn.events;

import com.fnproject.fn.testing.*;
import org.junit.*;

import static org.junit.Assert.*;

public class HelloFunctionTest {

    @Rule
    public final FnTestingRule testing = FnTestingRule.createDefault();

    @Test
    public void shouldReturnGreeting() {
        String payload = "hello";
        testing.givenEvent().withBody(payload).enqueue();
        testing.thenRun(HelloEventsFunction.class, "handleEvent");

        FnResult result = testing.getOnlyResult();
        assertEquals(payload, result.getBodyAsString());
    }

}