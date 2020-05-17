package com.example.lab11.config;

import com.example.lab11.jwt.JwtRequestFilter;
import com.example.lab11.jwt.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * An adapter for configuring the application's security.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * Method for web based security and access configuration.
     *
     * @param http an <code>HttpSecurity</code> instance which allows web based security configuration.
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.requiresChannel().anyRequest().requiresSecure().and().authorizeRequests().antMatchers("/**").permitAll();
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();*/
        http
                .csrf().disable().authorizeRequests().antMatchers(
                "/api/authenticate", "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
                "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
                .anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * assigns to the autowired <code>AuthenticationManagerBuilder</code> an <code>UserDetailsService</code>.
     *
     * @param auth an <code>AuthenticationManagerBuilder</code>.
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    /**
     * Ensures that the password does not suffer any form of encoding.
     *
     * @return a singleton of the `no operation` encoder <code>NoOpPasswordEncoder</code>.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Creates a functional `@Bean` for the <code>AuthenticationManager</code>.
     *
     * @return a newly created <code>AuthenticationManager</code>.
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
