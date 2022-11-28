package com.project.basic.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.basic.model.base.Base;
import com.project.basic.model.base.BaseSrch;
import com.project.basic.model.common.SearchList;
import com.project.basic.service.MainService;
import com.project.basic.utils.MessageUtil;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@AllArgsConstructor
public class MainController {
    
    MainService mainService;
    MessageSource messageSource;

    @GetMapping("/{id}")
    public String get(@PathVariable int id, Model model) {
        // Test DB
        Base base = mainService.get(id);

        // Message
        String message = MessageUtil.getMessage("alert.saved");
        
        model.addAttribute("base", base);
        model.addAttribute("message", message);
        
        return "main";
    }

    @GetMapping
    public String getList(@ModelAttribute BaseSrch option, Model model) {
        // Test DB
        SearchList<Base> baseList = mainService.getList(option);

        // Message
        String message = MessageUtil.getMessage("alert.saved");
        
        model.addAttribute("baseList", baseList);
        model.addAttribute("message", message);
        
        return "main";
    }
}