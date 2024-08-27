package com.sumit.spring.security.SpringSecurityBasic_01.service;


import com.sumit.spring.security.SpringSecurityBasic_01.entity.Book;

public interface BookService {

  Book saveBook(Book book);

  Book getBook(int id);
}
