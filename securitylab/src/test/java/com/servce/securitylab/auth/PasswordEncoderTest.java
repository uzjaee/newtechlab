package com.servce.securitylab.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Test
  @DisplayName("수동 등록 passwordEncoder 주입 받은 뒤 문자열 암호화 사용")
  void test1(){
    String password = "yongjae's password";
    String encodedPassword = passwordEncoder.encode(password);
    System.out.println("encodedPassword = " + encodedPassword);

    String inputPassword = "yongjae";
    boolean matches = passwordEncoder.matches(inputPassword, encodedPassword);
    System.out.println("matches = " + matches);
  }

}
