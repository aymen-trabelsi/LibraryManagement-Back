package com.codewithGAS.livre.controller;

import com.codewithGAS.livre.entity.Book;
import com.codewithGAS.livre.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(allowedHeaders = "**")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") Long bookId) {
        return bookService.findBookById(bookId);
    }

    @GetMapping("/all")
    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) {  bookService.deleteBook(bookId);  }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book,@PathVariable("id") Long bookId) {
        return bookService.updateBook(bookId);
    }

}
