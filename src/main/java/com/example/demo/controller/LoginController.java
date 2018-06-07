package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/login")
@Controller
public class LoginController {

    @GetMapping("/index")
    public String index(){
        return "/login/index";
    }

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public Map login(){
//        Map result = new HashMap();
//        return result;
//    }

    @GetMapping("test")
    public String test(){
        return "test";
    }

    @GetMapping("my")
    public String test2(){
        return "test2";
    }
}
