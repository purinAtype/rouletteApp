package com.example.rouletteApp.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.rouletteApp.form.EventForm;
import com.example.rouletteApp.model.RouletteEvent;
import com.example.rouletteApp.service.RouletteEventService;

@Controller
@RequestMapping("/event")
public class RouletteEventController {

    private final RouletteEventService service;

    public RouletteEventController(RouletteEventService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<RouletteEvent> events = service.findAll();
        model.addAttribute("events", events);
        return "event/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new EventForm());
        return "event/new";
    }

    @PostMapping("/new")
    public String submitForm(@ModelAttribute("form") @Valid EventForm form,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "event/new";
        }

        service.insert(form);
        return "redirect:/event/list";
    }
}
