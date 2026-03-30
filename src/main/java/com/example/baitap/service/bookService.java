package com.example.baitap.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.baitap.model.Book;
import com.example.baitap.repository.BookRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
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
            existingBook.setPrice(updateBook.getPrice());
            existingBook.setCategory(updateBook.getCategory());
            bookRepository.save(existingBook);
        }
    }
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
    public Page<Book> searchBooks(String keyword, Pageable pageable) {
        return bookRepository.findByTitleContaining(keyword, pageable);
    }
    public Page<Book> getBooksByCategory(int categoryId, Pageable pageable) {
        return bookRepository.findByCategoryId(categoryId, pageable);
    }
}



