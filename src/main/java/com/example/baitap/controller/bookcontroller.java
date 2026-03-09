package com.example.baitap.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.baitap.service.bookService;
import com.example.baitap.model.book;
import com.example.baitap.repository.CategoryRepository;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class bookcontroller {

    @Autowired
    private bookService bookService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/add")
    public String addBook(@RequestBody book b) {
        bookService.addBook(b);
        return "Book added";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable int id, @RequestBody book updateBook) {
        bookService.updateBook(id, updateBook);
        return "Book updated";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "Book deleted";
    }

    @GetMapping("/books")
    public List<book> getBooks() {
        book b1 = new book();
        b1.setTitle("Book 1");
        b1.setAuthor("Author 1");
        
        book b2 = new book();
        b2.setTitle("Book 2");
        b2.setAuthor("Author 2");
        
        book b3 = new book();
        b3.setTitle("Book 3");
        b3.setAuthor("Author 3");
        
        return Arrays.asList(b1, b2, b3);
    }
}