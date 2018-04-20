package com.example.teste.controller.service.impl;


import com.example.teste.controller.domain.entity.User;
import com.example.teste.controller.model.security.CerberusUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    void saveUser(final User user) throws Exception;

//    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
