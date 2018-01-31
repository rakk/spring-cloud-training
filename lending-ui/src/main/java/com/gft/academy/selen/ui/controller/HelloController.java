package com.gft.academy.selen.ui.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RefreshScope
@Controller
public class HelloController {

    @Value("${hello.city}")
    private String city;

    @GetMapping("/hello")
    String index(Model model) {
        model.addAttribute("city", city);
        return "hello";
    }
}
