package com.example.demo.controller;

import com.example.demo.base.utils.ValidateCodeUtil;
import com.example.demo.entity.LoginParam;
import com.example.demo.service.MainService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MainService mainService;

    @GetMapping("/login")
    public String index(){
        logger.info("test");
        return "/main/login";
    }

//    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
//    public String signIn(Model model){
//        model.addAttribute("errorFlag",true);
//        return "/main/login";
//    }

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

    @GetMapping("/getValidateCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response){
        try {
            ValidateCodeUtil.createImage(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/home")
    public String home(){
        return "/main/home";
    }
}
