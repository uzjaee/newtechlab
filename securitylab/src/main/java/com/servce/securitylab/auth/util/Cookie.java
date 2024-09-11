package com.servce.securitylab.auth.util;

import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Cookie {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  public static void addCookie(String cookieValue , HttpServletResponse res){
    try{
      cookieValue = URLEncoder.encode(cookieValue,"utf-8").replaceAll("\\+","%20");

      jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie(AUTHORIZATION_HEADER, cookieValue);  // name -value
      cookie.setPath("/");
      cookie.setMaxAge(30*60);

      res.addCookie(cookie);
    }catch ( UnsupportedEncodingException e){
      throw new RuntimeException(e.getMessage());
    }
  }

}
