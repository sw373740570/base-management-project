package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class BaseController {

    @Autowired
    protected HttpSession httpSession;
}
