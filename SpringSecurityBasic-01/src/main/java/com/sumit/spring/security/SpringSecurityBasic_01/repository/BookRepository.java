package com.sumit.spring.security.SpringSecurityBasic_01.repository;

import com.sumit.spring.security.SpringSecurityBasic_01.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
