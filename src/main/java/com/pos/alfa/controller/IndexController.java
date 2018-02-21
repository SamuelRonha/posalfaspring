package com.pos.alfa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/contaBancaria")
    public String conta() {
        return "/contaBancaria";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView test = new ModelAndView();
        test.addObject("Teste", "Something");
        return test;
    }

    @GetMapping("/error")
    public String error() {
        return "login";
    }
}
