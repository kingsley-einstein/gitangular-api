package com.gitangular.graph.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Auth extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(7);
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.csrf().disable();
    }

    @Override
    public void configure(WebSecurity security) throws Exception {
        security.ignoring().antMatchers(HttpMethod.OPTIONS);
    }
}