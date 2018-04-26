package com.example.model.factory;


import com.example.model.entity.User;
import com.example.model.entity.EntityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class UserFactory {

  public static EntityUser create(User user) {
    Collection<? extends GrantedAuthority> authorities;
    try {
      authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
    } catch (Exception e) {
      authorities = null;
    }
    return new EntityUser(
      user.getId(),
      user.getUsername(),
      user.getPassword(),
      user.getEmail(),
//      user.getLastPasswordReset(),
      authorities
    );
  }

}
