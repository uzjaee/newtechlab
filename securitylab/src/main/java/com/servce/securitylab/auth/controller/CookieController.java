package com.servce.securitylab.auth.controller;


import com.servce.securitylab.auth.util.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {
  public static final String AUTHORIZATION_HEADER = "Authorization";

  @GetMapping("/create-cookie")
  public String createCookie(HttpServletResponse res) {
    Cookie.addCookie("yj's cookie",res);
    return "createCookie";
  }


  @GetMapping("/get-cookie")
  public String getCookie(@CookieValue(AUTHORIZATION_HEADER) String value){
    System.out.println("value=" + value);

    return "get-cookie" + value;
  }

}
