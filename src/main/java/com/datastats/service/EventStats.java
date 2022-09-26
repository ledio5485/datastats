package com.datastats.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EventStats {
    private int total;
    private double sumX;
    private double sumY;

    @Override
    public String toString() {
        if (total == 0) {
            return String.format(Locale.ROOT,"%d,%.10f,%.10f,%.0f,%.3f", 0, 0, 0, 0, 0);
        }
        return String.format(Locale.ROOT,"%d,%.10f,%.10f,%.0f,%.3f", total, sumX, sumX/total, sumY, sumY/total);
    }
}
