package com.servce.securitylab.auth.controller.model;

import com.servce.securitylab.user.domain.Role;
import com.servce.securitylab.user.domain.User;
import lombok.Getter;

@Getter
public class SignUpRequest {
  private String email;
  private String password;
  private String name;
  private String nickname;
  private Role role;

  public User toEntity(String encodedPassword) {
    return User.of(encodedPassword,email, name, nickname,role);
  }

}
