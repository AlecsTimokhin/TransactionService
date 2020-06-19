package com.myorg.mainpack.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Value("${mainTitle}")
    private String mainTitle;

    @Value("${loginTitle}")
    private String loginTitle;


    @GetMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("title", mainTitle);
        return "mainPage";
    }


    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", loginTitle);
        return "users/login";
    }


}
