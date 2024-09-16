package com.servce.securitylab.jwt;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtUtils {

  public static SecretKey getSiningKey(String secretKey) {
    // base64로 문자열 치환
    byte[] keyBytes = Base64.getEncoder()
        .withoutPadding()
        .encode(secretKey.getBytes(UTF_8));
    // sha알고리즘으로 암호화 된 키 생성
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public static Date getExpirationDate(Instant issuedAt , int expirationMinutes) {
    Instant expriationInstant =issuedAt
        .plus(expirationMinutes, ChronoUnit.MINUTES);
    return Date.from(expriationInstant);
  }



}
