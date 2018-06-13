package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestBody Map<String, String> map){
        Map<String, Object> result = new HashMap<>();
        result.put("result", userService.getUserList(map));
        return result;
    }
}
