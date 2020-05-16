package com.example.lab11.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * An adapter for configuring the application's security.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Enables `https` and blocks `http` requests
     *
     * @param http an <code>HttpSecurity</code> instance which allows web based security configuration.
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requiresChannel()
                .anyRequest()
                .requiresSecure()
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll();
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}
