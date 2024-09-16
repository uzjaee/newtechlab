package com.servce.securitylab.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {
    // 각 요소별로 나눠서 검사하면 에러를 보내기도 쉬움
    // request header에서 authoriztion이 있는지 확인
    // 있다면 > 그 값을 꺼내온 뒤 bearer token 인지 확인
    // bearer 토큰이 맞다면 jwt 토큰인지 확인
    // 토큰의 유효성 검사
    // - 우리가 발급한 토큰이 맞는지
    // - 유효기간이 지나지는 않았는지
    // - 위/ 변조된 토큰은 아닌지
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (header == null || !header.startsWith("Bearer ")) {
      throw new IllegalArgumentException("올바른 header 형식이 아닙니다.");
    }
    String jwt = header.substring(7);
    String secretKey ="sample-secret-key-sample-secret-key-sample-secret-key-sample-secret-key-sample-secret-key";
    Claims claims = JwtResolver.resolve(jwt,secretKey);
    Long userId =  claims.get("id"  , Long.class);
    String role = claims.get("role", String.class);
    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
    System.out.println(grantedAuthority.getAuthority());

    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        userId,
        null,
        List.of(grantedAuthority)
    );
    SecurityContextHolder.getContext().setAuthentication(token);
    filterChain.doFilter(request,response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getRequestURI();
    // 특정 경로를 무시하도록 설정
    return path.startsWith("/auth") ;
  }

}
