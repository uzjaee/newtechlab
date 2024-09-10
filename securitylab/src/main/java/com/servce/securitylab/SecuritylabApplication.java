package com.servce.securitylab;

import jakarta.servlet.annotation.ServletSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SecuritylabApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecuritylabApplication.class, args);
  }

}
