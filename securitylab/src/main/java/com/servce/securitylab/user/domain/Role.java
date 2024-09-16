package com.servce.securitylab.user.domain;

import lombok.Getter;

@Getter
public enum Role {
  OWNER("고객"), CUSTOMER("가게주인"), MASTER("관리자");
  private final String description;
  Role(String description) {
    this.description = description;
  }
  public String getDescription() {
    return description;
  }
}
