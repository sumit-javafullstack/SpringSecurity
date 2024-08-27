package com.sumit.spring.security.SpringSecurityBasic_01.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping("/")
  public String login(HttpServletRequest request) {
    return "Hello " + request.getSession();
  }

  @GetMapping("/csrf-token")
  public CsrfToken getCsrfToken(HttpServletRequest request){
    return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
  }
}
