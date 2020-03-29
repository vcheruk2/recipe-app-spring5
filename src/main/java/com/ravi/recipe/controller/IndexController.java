package com.ravi.recipe.controller;

import com.ravi.recipe.service.BasicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final BasicService basicService;

    public IndexController(BasicService basicService) {
        this.basicService = basicService;
    }

    @RequestMapping({"/", "", "/index", "/index.html"})
    public String mapIndex(){
        System.out.println("hi 123");
        return "index";
    }
}
