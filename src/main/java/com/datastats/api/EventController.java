package com.datastats.api;

import com.datastats.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addEvents(@RequestBody String payload) {
        eventService.handleEvents(payload);
    }
}
