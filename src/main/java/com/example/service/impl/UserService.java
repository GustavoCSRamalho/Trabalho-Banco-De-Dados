package com.example.service.impl;


import com.example.domain.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    void saveUser(final User user) throws Exception;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
