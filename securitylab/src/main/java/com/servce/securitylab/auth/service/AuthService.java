package com.servce.securitylab.auth.service;

import com.servce.securitylab.auth.controller.model.SignInRequest;
import com.servce.securitylab.auth.controller.model.SignUpRequest;
import com.servce.securitylab.jwt.JwtProvider;
import com.servce.securitylab.user.domain.User;
import com.servce.securitylab.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

  private static final String DUPLICATED_EMAIL_ERROR_MESSAGE = "이미 존재하는 이메일입니다.";
  private static final String DUPLICATED_NICKNAME_ERROR_MESSAGE = "이미 존재하는 닉네임입니다.";
  public static final String INVALID_AUTHENTICATION_ERROR_MESSAGE = "사용자 정보가 올바르지 않습니다.";
  
  private final UserRepo userRepo;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public Long createUser(SignUpRequest signUpRequest){
    validate(signUpRequest);
    String password = passwordEncoder.encode(signUpRequest.getPassword());
    User user = signUpRequest.toEntity(password);
    User  createdUser = userRepo.save(user);
    return createdUser.getId();
  }

  private void validate(SignUpRequest signUpRequest) {
    validateDuplicatedEmail(signUpRequest.getEmail());
    validateDuplicatedNickname(signUpRequest.getNickname());
  }

  private void validateDuplicatedNickname(String nickname) {
    boolean exits = userRepo.existsByNickname(nickname);
    if(exits){
      throw new IllegalArgumentException(DUPLICATED_NICKNAME_ERROR_MESSAGE);
    }
  }

  private void validateDuplicatedEmail(String email) {
    boolean exits = userRepo.existsByEmail(email);
    if(exits){
      throw new IllegalArgumentException(DUPLICATED_EMAIL_ERROR_MESSAGE);
    }
  }

  public String authentication(SignInRequest signInRequest) {
    User user = userRepo.findByEmail(signInRequest.getEmail())
        .orElseThrow(
        () -> new IllegalArgumentException(INVALID_AUTHENTICATION_ERROR_MESSAGE)
    );
    boolean matches = passwordEncoder.matches(signInRequest.getPassword(),user.getPassword());

    if(!matches){
      throw new IllegalArgumentException(INVALID_AUTHENTICATION_ERROR_MESSAGE);
    }
    //todo jwt 발급
    return new JwtProvider().provider(user.getId());


  }

}
