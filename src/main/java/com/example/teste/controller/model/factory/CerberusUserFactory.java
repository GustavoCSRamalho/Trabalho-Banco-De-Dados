package com.example.teste.controller.model.factory;


import com.example.teste.controller.domain.entity.User;
import com.example.teste.controller.model.security.CerberusUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class CerberusUserFactory {

  public static CerberusUser create(User user) {
    Collection<? extends GrantedAuthority> authorities;
    try {
      authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
    } catch (Exception e) {
      authorities = null;
    }
    return new CerberusUser(
      user.getId(),
      user.getUsername(),
      user.getPassword(),
      user.getEmail(),
//      user.getLastPasswordReset(),
      authorities
    );
  }

}
