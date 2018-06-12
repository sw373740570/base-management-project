package com.example.demo.controller;

import com.example.demo.base.utils.ValidateCodeUtil;
import com.example.demo.entity.LoginParam;
import com.example.demo.service.MainService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@RequestMapping("/main")
@Controller
public class MainController extends BaseController{


    @Autowired
    private UserService userService;

    @Autowired
    private MainService mainService;

    @GetMapping("/login")
    public String index(){
        return "/main/login";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String signIn(Model model){
        model.addAttribute("errorFlag",true);
        return "/main/login";
    }

    @GetMapping(value = {"/index","/",""})
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

    @GetMapping("/getValidateCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response){
        try {
            ValidateCodeUtil.createImage(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
