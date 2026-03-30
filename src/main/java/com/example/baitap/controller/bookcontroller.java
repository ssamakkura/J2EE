package com.example.baitap.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.baitap.service.BookService;
import com.example.baitap.model.Book;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api")

public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @PostMapping("/")
    public String addBook(@RequestBody Book b) {
        bookService.addBook(b);
        System.out.println("ok: " + b);
        return "Book added";
    }

    // @GetMapping("/{id}")
    // public book getBookById(@PathVariable int id) {
    // return bookService.getBookById(id);
    // }

    // @PutMapping("/{id}")
    // public String updateBook(@PathVariable int id, @RequestBody book updateBook)
    // {
    // bookService.updateBook(id, updateBook);
    // return "Book updated";
    // }

    // @DeleteMapping("/{id}")
    // public String deleteBook(@PathVariable int id) {
    // bookService.deleteBook(id);
    // return "Book deleted";
    // }

    @GetMapping("/books")
    public List<Book> getBooks() {
        Book b1 = new Book();
        b1.setTitle("Book 1");
        b1.setAuthor("Author 1");

        Book b2 = new Book();
        b2.setTitle("Book 2");
        b2.setAuthor("Author 2");

        Book b3 = new Book();
        b3.setTitle("Book 3");
        b3.setAuthor("Author 3");

        return Arrays.asList(b1, b2, b3);
    }
}