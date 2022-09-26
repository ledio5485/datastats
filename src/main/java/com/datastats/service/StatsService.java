package com.datastats.service;

import com.datastats.persistence.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final EventRepository eventRepository;

    public EventStats getLastMinuteStats() {
        Long toTimestamp = System.currentTimeMillis() - 60 * 1000;
        List<EventData> events = eventRepository.getLastEvents(toTimestamp);

        return events.isEmpty() ? new EventStats() : events.stream().reduce(
                new EventStats(),
                (stat, data) -> new EventStats(stat.getTotal() + 1, stat.getSumX() + data.getX(), stat.getSumY() + data.getY()),
                (stats1, stats2) -> new EventStats(stats1.getTotal() + stats2.getTotal(), stats1.getSumX() + stats2.getSumX(), stats1.getSumY() + stats2.getSumY())
        );
    }
}
