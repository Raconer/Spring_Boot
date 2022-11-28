package com.project.basic.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.basic.model.Base;
import com.project.basic.service.MainService;
import com.project.basic.utils.MessageUtil;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {
    
    MainService mainService;
    MessageSource messageSource;

    @GetMapping
    public String getMain(Model model) {
        // Test DB
        Base base = mainService.getMain();

        // Message
        String message = MessageUtil.getMessage("alert.saved");
        
        model.addAttribute("base", base);
        model.addAttribute("message", message);
        
        return "main";
    }
}