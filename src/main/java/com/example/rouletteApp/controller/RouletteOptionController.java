package com.example.rouletteApp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.rouletteApp.form.OptionForm;
import com.example.rouletteApp.model.RouletteCategory;
import com.example.rouletteApp.model.RouletteOption;
import com.example.rouletteApp.service.RouletteCategoryService;
import com.example.rouletteApp.service.RouletteOptionService;

@Controller
@RequestMapping("/option")
public class RouletteOptionController {

    private final RouletteOptionService optionService;
    private final RouletteCategoryService categoryService;

    public RouletteOptionController(RouletteOptionService optionService,
                                    RouletteCategoryService categoryService) {
        this.optionService = optionService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list/{categoryId}")
    public String list(@PathVariable Integer categoryId, Model model) {
        List<RouletteOption> options = optionService.findByCategoryId(categoryId);
        model.addAttribute("options", options);
        model.addAttribute("categoryId", categoryId);

        // eventId を取得して渡す
        RouletteCategory category = categoryService.findById(categoryId);
        model.addAttribute("eventId", category != null ? category.getEventId() : null);

        return "option/list";
    }

    @GetMapping("/new/{categoryId}")
    public String newForm(@PathVariable Integer categoryId, Model model) {
        OptionForm form = new OptionForm();
        form.setCategoryId(categoryId);
        model.addAttribute("form", form);
        return "option/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute OptionForm form) {
        optionService.insert(form);
        return "redirect:/option/list/" + form.getCategoryId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        OptionForm form = optionService.getFormById(id);
        model.addAttribute("form", form);
        return "option/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute OptionForm form) {
        optionService.update(form);
        return "redirect:/option/list/" + form.getCategoryId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Integer categoryId = optionService.delete(id);
        return "redirect:/option/list/" + categoryId;
    }
}
