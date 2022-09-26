package com.datastats.persistence;

import com.datastats.service.EventData;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    public static final int ONE_SECOND = 1_000;
    private final SortedMap<Long, List<EventData>> events = new TreeMap<>(Comparator.reverseOrder());

    public void addEvents(List<EventData> events) {
        events.forEach(eventData -> {
            Long bucket = ONE_SECOND * (eventData.getTimestamp() / ONE_SECOND);
            if (this.events.containsKey(bucket)) {
                this.events.get(bucket).add(eventData);
            } else {
                this.events.put(bucket, new ArrayList<>(Collections.singletonList(eventData)));
            }
        });
    }

    public List<EventData> getLastEvents(Long toTimestamp) {
        Long bucket = ONE_SECOND * (toTimestamp / ONE_SECOND);
        return this.events.headMap(bucket)
                .values()
                .stream()
                .flatMap(Collection::stream)
                .filter(eventData -> eventData.getTimestamp() >= toTimestamp)
                .collect(Collectors.toList());
    }
}
