package com.datastats.service;

import com.datastats.persistence.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;
    public void handleEvents(String payload) {
        eventRepository.addEvents(eventConverter.convert(payload));
    }
}
