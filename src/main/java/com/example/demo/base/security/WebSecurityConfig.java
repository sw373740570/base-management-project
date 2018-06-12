package com.example.demo.base.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Bean
    public ValidateCodeAuthenticationFilter getValidateCodeAuthenticationFilter() throws Exception {
        ValidateCodeAuthenticationFilter filter =  new ValidateCodeAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(getValidateCodeAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/getValidateCode","/druid/**","/signIn","/login").permitAll()
                .antMatchers("/css/**", "/js/**","/images/**", "/webjars/**", "**/favicon.ico", "/assets/**", "/font/**", "/*.html").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginPage("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(getAuthenticationProvider());
        auth.authenticationProvider(getAuthenticationProvider());
    }
}
