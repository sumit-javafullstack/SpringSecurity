package com.sumit.spring.security.SpringSecurityBasic_01.service;

import com.sumit.spring.security.SpringSecurityBasic_01.entity.User;

public interface UserService {

    public User registerUser(User user);

    public User loginUser(User user);
}
