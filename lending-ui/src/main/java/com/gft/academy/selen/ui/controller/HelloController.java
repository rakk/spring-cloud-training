package com.gft.academy.selen.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RefreshScope
@Controller
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${hello.city}")
    private String city;

    @GetMapping("/hello")
    String index(Model model) {
        logger.debug("inside index when city is {}", city);
        model.addAttribute("city", city);
        return "hello";
    }
}
