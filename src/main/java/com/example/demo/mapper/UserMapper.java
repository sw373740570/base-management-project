package com.example.demo.mapper;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User findByUsername(String username);

    int addUser(Map<String, String> map);

    List<com.example.demo.model.User> getUserList(Map<String, String> map);
}
