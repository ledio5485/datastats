package com.datastats.service;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventConverter {

    public List<EventData> convert(String payload) {
        return payload.lines()
                .map(this::createEventData)
                .collect(Collectors.toList());
    }

    private EventData createEventData(String line) {
        String[] data = line.split(",");
        return new EventData(data[0], data[1], data[2]);
    }
}
