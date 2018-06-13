package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<User> getUserList(Map<String, String> map) {
        PageInfo pageInfo = new PageInfo();
//        PageHelper.startPage(2,10);
        pageInfo.setList(userMapper.getUserList(map));
        return pageInfo;
    }
}
