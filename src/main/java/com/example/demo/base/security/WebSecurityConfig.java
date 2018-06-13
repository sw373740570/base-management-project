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
                .antMatchers("/css/**", "/js/**","/images/**", "/webjars/**", "**/favicon.ico", "/assets/**", "/font/**", "/user/**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                /**
                 * spring Security下，X-Frame-Options默认为DENY,非Spring Security环境下，X-Frame-Options的默认大多也是DENY，这种情况下，浏览器拒绝当前页面加载任何Frame页面，设置含义如下：
                 *
                 *     DENY：浏览器拒绝当前页面加载任何Frame页面
                 *     SAMEORIGIN：frame页面的地址只能为同源域名下的页面
                 *     ALLOW-FROM：origin为允许frame加载的页面地址。
                 */
                .and().headers().frameOptions().sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(getAuthenticationProvider());
        auth.authenticationProvider(getAuthenticationProvider());
    }
}
