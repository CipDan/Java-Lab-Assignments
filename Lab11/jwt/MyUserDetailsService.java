package com.example.lab11.jwt;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Implementation of a custom <code>UserDetailsService</code>.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    /**
     * @param username the username of a possible user.
     * @return a newly created user.
     * @throws UsernameNotFoundException when the given username does not match any entry.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("ala", "bala", new ArrayList<>());
    }
}
