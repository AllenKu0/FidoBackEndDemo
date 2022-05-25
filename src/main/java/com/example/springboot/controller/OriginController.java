package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class OriginController {
    @RequestMapping("/index")
    @ApiIgnore
    public String testController(@RequestParam(value = "title",defaultValue = "xiao",
            required = false )String title, Model model){
        model.addAttribute("name",title);
        return "home";
    }

    @RequestMapping("/index2")
    @ApiIgnore
    public void test(){

    }
}
