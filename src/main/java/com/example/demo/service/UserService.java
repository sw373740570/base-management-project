package com.example.demo.service;

import com.example.demo.model.User;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface UserService {

    int addUser(Map<String, String> map);

    PageInfo<User> getUserList(Map<String, String> map);
}
