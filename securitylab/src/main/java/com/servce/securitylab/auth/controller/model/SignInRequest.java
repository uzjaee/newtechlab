package com.servce.securitylab.auth.controller.model;

import lombok.Getter;

@Getter
public class SignInRequest {
  private String email;
  private String password;

}
