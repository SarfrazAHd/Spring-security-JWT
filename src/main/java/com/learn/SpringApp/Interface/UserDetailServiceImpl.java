package com.learn.SpringApp.Interface;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("dummyClientId")){
           /* password: clientSecret*/
            return new User("dummyClientId","$2a$12$AEijtcCK4vbFZN9ya4G1curaoolblMRb3c4wqM5AYz4ZAXwVdhO0m", new ArrayList<>());
        }
        else throw new UsernameNotFoundException("ClientID not found.");
    }
}
