package com.example.teste.controller.domain.entity;

import com.example.teste.controller.domain.base.DomainBase;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "email")
  private String email;
//  private Date lastPasswordReset;
  @Column(name = "authorities")
  private String authorities;

  public User(){}

  public User(String username, String password, String email, String authorities) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.authorities = authorities;
  }

//  public User() {
//    super();
//  }
//
//  public User(Long id,String username, String password, String email, String authorities) {
//    this.setId(id);
//    this.setUsername(username);
//    this.setPassword(password);
//    this.setEmail(email);
////    this.setLastPasswordReset(lastPasswordReset);
//    this.setAuthorities(authorities);
//  }



}
