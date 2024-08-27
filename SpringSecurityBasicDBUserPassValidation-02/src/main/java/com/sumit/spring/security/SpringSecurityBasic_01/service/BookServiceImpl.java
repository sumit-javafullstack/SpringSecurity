package com.sumit.spring.security.SpringSecurityBasic_01.service;


import com.sumit.spring.security.SpringSecurityBasic_01.entity.Book;
import com.sumit.spring.security.SpringSecurityBasic_01.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  public Book getBook(int id) {
    return bookRepository.findById(id).orElse(null);
  }
}
