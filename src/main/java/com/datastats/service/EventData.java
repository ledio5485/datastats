package com.datastats.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class EventData {
    private final long timestamp;
    private final double x;
    private final int y;

    public EventData(String timestamp, String x, String y) {
        this(Long.parseLong(timestamp), Double.parseDouble(x), Integer.parseInt(y));
    }
}
