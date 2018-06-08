package com.example.demo.base.security;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DBAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        User user = (User)userService.loadUserByUsername(username);
        if(user == null){
            throw new BadCredentialsException("用户不存在");
        }
        if(!password.equals(user.getPassword())){
            throw new BadCredentialsException("密码错误");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user,password,authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
