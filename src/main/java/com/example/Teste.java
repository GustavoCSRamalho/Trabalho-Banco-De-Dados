package com.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;

public class Teste {

    public static void main(String args[]) throws SQLException{
        String URL = "jdbc:oracle:thin:USER_BD@//localhost:1521/xe";
        String USER = "SYSTEM";
        String PASS = "aluno";


        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
        }
        catch(Exception ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
    }
}

