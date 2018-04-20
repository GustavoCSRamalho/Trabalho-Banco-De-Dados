package com.example.teste.controller.service.impl;


import com.example.teste.controller.domain.entity.User;
import com.example.teste.controller.model.factory.CerberusUserFactory;
import com.example.teste.controller.model.security.CerberusUser;
import com.example.teste.controller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    } else {
      return CerberusUserFactory.create(user);
    }
  }

  @Override
  public void saveUser(User user){
    try{
      user.setAuthorities("ADMIN");
    this.userRepository.save(user);
  }catch(Exception e){
      e.printStackTrace();
    }
  }

}
