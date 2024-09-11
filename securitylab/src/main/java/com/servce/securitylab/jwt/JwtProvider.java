package com.servce.securitylab.jwt;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtProvider {
  // 사용자를 특정 할 수 있는 정보가 필요함
  // id , email ,nickname ( pk .. uk)
  // newtech-server-secret-key-sparta-27io -> Base64 로 암호화된 키를 signWith에 넣기

  public String provider(Long id){
    String secretKey ="sample-secret-key";

    SecretKey signingKey = getSiningKey(secretKey);
    Instant issuedAt = Instant.now();
    Date expirationDate = getExpirationDate(issuedAt,30);

    return Jwts
        .builder()
        .issuer("newtechlab server")
        .issuedAt(Date.from(issuedAt)) // 발급일자
        .expiration(expirationDate)  // 만료일자 , 10분( 연습이니까 일단 30분) , 리프레시 토큰은 또 따로 존재

        .audience().add("newtechlab client").and()  // 청중  => 발급 대상 모놀리틱 , Msa 임을 알 수 있다.
        .claim("id", id)

        .signWith(signingKey)
        .compact();
  }

  private SecretKey getSiningKey(String secretKey) {
    // base64로 문자열 치환
    byte[] keyBytes = Base64.getEncoder()
        .withoutPadding()
        .encode(secretKey.getBytes(UTF_8));
    // sha알고리즘으로 암호화 된 키 생성
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private Date getExpirationDate(Instant issuedAt , int expirationMinutes) {
    Instant expriationInstant =issuedAt
        .plus(expirationMinutes, ChronoUnit.MINUTES);
    return Date.from(expriationInstant);
  }

}
