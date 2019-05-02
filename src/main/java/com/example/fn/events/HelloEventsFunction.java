package com.example.fn.events;


public class HelloEventsFunction {

    public String handleEvent(String eventPayload) {
        System.out.println("Events service payload\n" + eventPayload);
        return eventPayload;
    }
}