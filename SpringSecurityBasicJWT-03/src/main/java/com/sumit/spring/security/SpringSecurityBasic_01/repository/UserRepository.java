package com.sumit.spring.security.SpringSecurityBasic_01.repository;

import com.sumit.spring.security.SpringSecurityBasic_01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
