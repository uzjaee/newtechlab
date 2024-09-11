package com.servce.securitylab.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(
    name = "p_user",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UK_USER_EMAIL",
            columnNames = "email"
        ),
        @UniqueConstraint(
            name = "UK_USER_NICKNAME",
            columnNames = "nickname"
        )
    }
)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false, length = 45)
  private String name;

  @Column(nullable = false, length = 45)
  private String nickname;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 45)
  private Role role;

  @Column(nullable = false, length = 200)
  private String password;

  private User(String password, String nickname, String name, String email, Role role) {
    this.password = password;
    this.nickname = nickname;
    this.name = name;
    this.email = email;
    this.role = role;
  }
  public static User of(String password, String nickname, String name, String email, Role role){
    return new User(password, nickname, name, email,role);
  }


}
