package com.sumit.spring.security.SpringSecurityBasic_01.service;

import com.sumit.spring.security.SpringSecurityBasic_01.entity.User;
import com.sumit.spring.security.SpringSecurityBasic_01.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements  UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User registerUser(User user) {
         user.setPassword(encoder.encode(user.getPassword()));
         User savedUser = userRepository.save(user);
         log.info("Users in database : {}",userRepository.findAll());
         return savedUser;
    }

    @Override
    public User loginUser(User user) {
        return null;
    }
}
