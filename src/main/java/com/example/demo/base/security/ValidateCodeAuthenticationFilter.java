package com.example.demo.base.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidateCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    protected ValidateCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
        SimpleUrlAuthenticationFailureHandler failedHandler = (SimpleUrlAuthenticationFailureHandler)getFailureHandler();
        failedHandler.setDefaultFailureUrl("/login?error");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String code = (String)httpServletRequest.getSession().getAttribute("validateCode");
        String inputCode = httpServletRequest.getParameter("code");
        if (StringUtils.isEmpty(inputCode) || !inputCode.toUpperCase().equals(code)){
            throw new BadCredentialsException("验证码错误");
        }else {
            httpServletRequest.getSession().removeAttribute("validateCode");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(httpServletRequest.getParameter("username"),httpServletRequest.getParameter("password"));
        return this.getAuthenticationManager().authenticate(token);
    }
}
