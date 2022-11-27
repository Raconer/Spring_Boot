package com.project.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    
    @GetMapping
    public String getMain() {
        return "/WEB-INF/views/main.jsp";
    }
}