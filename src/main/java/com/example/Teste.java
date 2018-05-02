package com.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Teste {
    public static void main(String[] args){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String c = bCryptPasswordEncoder.encode("123");
        System.out.println("SENHA : "+c);
    }

}
