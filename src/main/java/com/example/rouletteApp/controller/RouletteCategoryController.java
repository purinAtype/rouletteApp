package com.example.rouletteApp.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.rouletteApp.form.CategoryForm;
import com.example.rouletteApp.model.RouletteCategory;
import com.example.rouletteApp.service.RouletteCategoryService;

@Controller
@RequestMapping("/category")
public class RouletteCategoryController {

    private final RouletteCategoryService service;

    public RouletteCategoryController(RouletteCategoryService service) {
        this.service = service;
    }

    @GetMapping("/list/{eventId}")
    public String list(@PathVariable Integer eventId, Model model) {
        List<RouletteCategory> categories = service.findByEventId(eventId);
        model.addAttribute("categories", categories);
        model.addAttribute("eventId", eventId);
        return "category/list";
    }

    @GetMapping("/new/{eventId}")
    public String newForm(@PathVariable Integer eventId, Model model) {
        CategoryForm form = new CategoryForm();
        form.setEventId(eventId);
        model.addAttribute("form", form);
        return "category/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("form") @Valid CategoryForm form,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return "category/new";
        }
        service.insert(form);
        return "redirect:/category/list/" + form.getEventId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        CategoryForm form = service.getFormById(id);
        model.addAttribute("form", form);
        return "category/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("form") @Valid CategoryForm form,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return "category/edit";
        }
        service.update(form);
        return "redirect:/category/list/" + form.getEventId();
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Integer eventId = service.delete(id);
        return "redirect:/category/list/" + eventId;
    }
    
    @PostMapping("/spin/{id}")
    public String spin(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        String result = service.spinRoulette(id);
        redirectAttributes.addFlashAttribute("message", "ルーレット結果: " + result);
        Integer eventId = service.getEventIdByCategoryId(id);
        return "redirect:/category/list/" + eventId;
    }
}
