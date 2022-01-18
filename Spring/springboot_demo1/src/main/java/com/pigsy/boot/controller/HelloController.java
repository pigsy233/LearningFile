package com.pigsy.boot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class HelloController {

    @RequestMapping("/hellopigeon")
    public String handle01(){
        return "fucking liu in spring boot 2";
    }
}