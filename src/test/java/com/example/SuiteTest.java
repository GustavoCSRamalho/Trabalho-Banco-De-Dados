package com.example;

import com.example.integration.AuthenticationTest;
import com.example.integration.BookTeste;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookTeste.class, AuthenticationTest.class})
public class SuiteTest {

}
