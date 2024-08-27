package com.sumit.spring.security.SpringSecurityBasic_01.security.service;

import com.sumit.spring.security.SpringSecurityBasic_01.entity.User;
import com.sumit.spring.security.SpringSecurityBasic_01.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null){
            log.info("User doesn't exist");
            throw new UsernameNotFoundException("User doesn't exist");
        }
        UserPrinciple userPrinciple = new UserPrinciple(user);


        return userPrinciple;
    }
}
