package com.example.teste.controller.repository;

import com.example.teste.controller.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

  public User findByUsername(String username);




}
