package com.example.demo.mapper;

import com.example.demo.entity.User;

import java.util.Map;

public interface UserMapper {

    User findByUsername(String username);

    int addUser(Map<String, String> map);
}
