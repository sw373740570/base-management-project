package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(Map<String, String> map) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        map.put("password",encoder.encode(map.get("password")));
        userMapper.addUser(map);
        return 0;
    }
}
