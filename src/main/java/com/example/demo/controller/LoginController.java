package com.example.demo.controller;

import com.example.demo.entity.LoginParam;
import com.example.demo.service.MainService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@RequestMapping("/main")
@Controller
public class LoginController extends BaseController{


    @Autowired
    private UserService userService;

    @Autowired
    private MainService mainService;

    @GetMapping("/login")
    public String index(){
        return "/main/login";
    }

    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody LoginParam loginParam){
        Map result = new HashMap();
        mainService.signIn(loginParam);
        result.put("code",200);
        return result;
    }

    @GetMapping("/index")
    public String test(){
        return "/main/base";
    }

    @PostMapping("/addUser")
    @ResponseBody
    public String test2(@RequestBody Map<String, String> map){
        userService.addUser(map);
        return "success";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test3(){
        //userService.addUser(map);
        return "success";
    }
}
