package com.servce.securitylab.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.WeakKeyException;
import jakarta.security.auth.message.AuthException;
import javax.crypto.SecretKey;

public class JwtResolver {
  public static Claims resolve(String jwt, String secretKey) {
    SecretKey signingKey = JwtUtils.getSiningKey(secretKey);

    try {
      return Jwts.parser()
          .verifyWith(signingKey)
          .build()
          .parseSignedClaims(jwt)
          .getPayload();
    }
    catch (WeakKeyException | NullPointerException weakKeyException) {
      throw new IllegalArgumentException("잘못된 Key");
    } catch (ExpiredJwtException expiredJwtException) {
      throw new IllegalArgumentException("유효기간이 지난 토큰입니다.");
    } catch (MalformedJwtException malformedJwtException) {
      throw new IllegalArgumentException("jwt 형식이 아닙니다. ");
    } catch (Exception unexpectedException) {
      throw new RuntimeException(unexpectedException);
    }
  }

}
