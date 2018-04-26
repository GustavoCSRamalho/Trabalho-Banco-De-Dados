package com.example.service.impl;


import com.example.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    void saveUser(final User user) throws Exception;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
