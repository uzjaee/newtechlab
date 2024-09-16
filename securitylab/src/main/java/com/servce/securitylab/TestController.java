package com.servce.securitylab;

import com.servce.securitylab.user.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/test")
  public void test() {
    System.out.println("admin 유저만 접근할 수 있습니다.");
    System.out.println("테스트 성공입니다.");
  }

}
