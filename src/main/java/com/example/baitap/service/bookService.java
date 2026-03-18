package com.example.baitap.service;

import java.util.List;
import java.util.ArrayList;

import com.example.baitap.model.Book;
import com.example.baitap.repository.BookRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }
    public void addBook(Book b) {
        bookRepository.save(b);
    }
    public void updateBook(int id, Book updateBook)
    {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(updateBook.getTitle());
            existingBook.setAuthor(updateBook.getAuthor());
            existingBook.setCategory(updateBook.getCategory());
            bookRepository.save(existingBook);
        }
    }
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
