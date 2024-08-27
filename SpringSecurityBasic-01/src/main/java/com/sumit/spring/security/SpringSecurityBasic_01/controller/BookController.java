package com.sumit.spring.security.SpringSecurityBasic_01.controller;


import com.sumit.spring.security.SpringSecurityBasic_01.entity.Book;
import com.sumit.spring.security.SpringSecurityBasic_01.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/saveBook")
    public Book saveBook(@RequestBody Book book){
        return  bookService.saveBook(book);
    }

    @GetMapping("/getBook/{id}")
    public Book getBook(@PathVariable("id") int id){
        return  bookService.getBook(id);
    }


}
