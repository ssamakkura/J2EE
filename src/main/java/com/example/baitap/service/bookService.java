package com.example.baitap.service;

import java.util.List;
import java.util.ArrayList;

import com.example.baitap.model.book;

import org.springframework.stereotype.Service;

@Service
public class bookService {
    private List<book> books = new ArrayList<>();
    public List<book> getAllBooks() {
        return books;
    }
    public book getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }
    public void addBook(book b) {
        int newId = books.stream()
                .mapToInt(book::getId)
                .max()
                .orElse(0) + 1;

        b.setId(newId);
        books.add(b);
    }
    public void updateBook(int id, book updateBook)
    {
        books.stream()
            .filter(book -> book.getId() == id)
            .findFirst()
            .ifPresent(book -> {
                book.setTitle(updateBook.getTitle());
                book.setAuthor(updateBook.getAuthor());
            });
    }
    public void deleteBook(int id) {
        books.removeIf(b -> b.getId() == id);
    }
}
