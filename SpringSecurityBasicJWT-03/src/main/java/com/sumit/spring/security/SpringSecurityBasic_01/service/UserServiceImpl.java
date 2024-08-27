package com.sumit.spring.security.SpringSecurityBasic_01.service;

import com.sumit.spring.security.SpringSecurityBasic_01.entity.User;
import com.sumit.spring.security.SpringSecurityBasic_01.repository.UserRepository;
import com.sumit.spring.security.SpringSecurityBasic_01.security.jwt.JWTGenerateTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  @Autowired private UserRepository userRepository;

  @Autowired private BCryptPasswordEncoder encoder;

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JWTGenerateTokenService jwtGenerateTokenService;

  @Override
  public User registerUser(User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);
    log.info("Users in database : {}", userRepository.findAll());
    return savedUser;
  }

  @Override
  public String verify(User user) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    if (authentication.isAuthenticated()) {
      return jwtGenerateTokenService.generateToken(user.getUsername());
    }
    return "Fail";
  }
}
