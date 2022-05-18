package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OriginController {
    @RequestMapping("/index")
    public String testController(@RequestParam(value = "title",defaultValue = "xiao",
            required = false )String title, Model model){
        model.addAttribute("name",title);
        return "index";
    }

    @RequestMapping("/index2")
    public void test(){

    }
}
