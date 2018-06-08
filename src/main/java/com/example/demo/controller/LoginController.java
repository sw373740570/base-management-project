package com.example.demo.controller;

import com.example.demo.entity.LoginParam;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/")
@Controller
public class LoginController extends BaseController{


    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String index(){
        return "/main/login";
    }

    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public Map login(@RequestBody LoginParam loginParam){
        Map result = new HashMap();
        return result;
    }

    @GetMapping("main")
    public String test(){
        return "/main/basc";
    }

    @PostMapping("addUser")
    @ResponseBody
    public String test2(@RequestBody Map<String, String> map){
        userService.addUser(map);
        return "success";
    }
}
