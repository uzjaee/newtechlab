package com.servce.securitylab.auth.controller;

import com.servce.securitylab.auth.controller.model.SignInRequest;
import com.servce.securitylab.auth.controller.model.SignUpRequest;
import com.servce.securitylab.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping(
    path ="/auth",
    consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
    produces = MimeTypeUtils.APPLICATION_JSON_VALUE
)
public class AuthController {
  private final AuthService authService;
  @PostMapping("/sign-up")
  ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest) {
    Long userId = authService.createUser(signUpRequest);

    URI  userUri = UriComponentsBuilder.fromUriString("/user/{userId}")
        .buildAndExpand(userId)
        .toUri();
    return ResponseEntity.created(userUri).build();
  }

  @PostMapping("/sign-in")
  ResponseEntity<Void> signIn(
      @RequestBody SignInRequest signInRequest,
      HttpServletResponse res){
    String accessToken = authService.authentication(signInRequest);
    res.setHeader(HttpHeaders.AUTHORIZATION, accessToken);
    return ResponseEntity.ok().build();
  }

}
