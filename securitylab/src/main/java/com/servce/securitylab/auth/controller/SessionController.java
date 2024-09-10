package com.servce.securitylab.auth.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
  public static final String AUTHORIZATION_HEADER = "Authorization";

  @GetMapping("/create-session")
  public String createSession(HttpServletRequest req){
    HttpSession session = req.getSession(true); // 세션이 존재할 경우 반환 없을경우 새션 생성후 반환
    session.setAttribute(AUTHORIZATION_HEADER, "yj's cookie");

    return "create-session";
  }

  @GetMapping("/get-session")
  public String getSession(HttpServletRequest req){
    HttpSession session = req.getSession(false);
    String value = session.getAttribute(AUTHORIZATION_HEADER).toString();
    return value;
  }
}
