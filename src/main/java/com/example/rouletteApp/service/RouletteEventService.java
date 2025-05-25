package com.example.rouletteApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rouletteApp.form.EventForm;
import com.example.rouletteApp.mapper.RouletteEventMapper;
import com.example.rouletteApp.model.RouletteEvent;

@Service
public class RouletteEventService {

    private final RouletteEventMapper mapper;

    public RouletteEventService(RouletteEventMapper mapper) {
        this.mapper = mapper;
    }

    public List<RouletteEvent> findAll() {
        return mapper.selectByExample(null);
    }

    public void insert(EventForm form) {
        RouletteEvent event = new RouletteEvent();
        event.setEventName(form.getEventName());
        mapper.insertSelective(event);
    }
}
