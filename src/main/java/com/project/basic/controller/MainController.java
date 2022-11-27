package com.project.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.basic.model.Base;
import com.project.basic.repository.MainMapper;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {
    
    MainMapper mainMapper;


    @GetMapping
    public String getMain(Model model) {
        Base base = mainMapper.getMain();
        model.addAttribute("base", base);
        return "main";
    }
}