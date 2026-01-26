package com.example.baitap.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.baitap.service.bookService;
import com.example.baitap.model.book;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/books")
public class bookcontroller {
    @Autowired
    private bookService bookService;
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
}
