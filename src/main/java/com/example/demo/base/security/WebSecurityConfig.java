package com.example.demo.base.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserService getUserService(){
        return new UserService();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider(){
        return new DBAuthenticationProvider();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/addUser","/druid/**","/login/**").permitAll()
                .antMatchers("/css/**", "/js/**","/images/**", "/webjars/**", "**/favicon.ico", "/assets/**", "/font/**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginPage("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getAuthenticationProvider());
    }
}
