package com.sumit.spring.security.SpringSecurityBasic_01.controller;

import com.sumit.spring.security.SpringSecurityBasic_01.entity.User;
import com.sumit.spring.security.SpringSecurityBasic_01.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/public/test")
  public String parent(HttpServletRequest request) {
    return "Hello from public url" + request.getSession();
  }

  @GetMapping("/login")
  public String login(HttpServletRequest request) {
    return "Hello from login " + request.getSession();
  }

  @PostMapping("/register")
  public User register(@RequestBody User user) {

  return userService.registerUser(user);
  }

}
