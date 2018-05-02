//package com.example.service.impl;
//
//import com.example.service.SecurityService;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class SecurityServiceImpl implements SecurityService {
//
//  @Override
//  public Boolean hasProtectedAccess() {
//    return (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")));
//  }
//
//  @Override
//  public Boolean hasProtectedAdminAccess(){
//    List<String> lista = new ArrayList();
//    lista.add("ADMIN");
//    lista.add("USER");
//    return (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(lista));
//  }
//
//}
