package com.example.baitap.controller;

import com.example.baitap.model.book;
import com.example.baitap.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookViewController {

    @Autowired
    private bookService bookService;

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<book> list = bookService.getAllBooks();
        model.addAttribute("books", list);
        return "book"; 
    }

    @GetMapping("/add-book")
    public String showAddForm(Model model) {
        model.addAttribute("book", new book());
        return "add_book"; 
    }

    @PostMapping("/add-book")
    public String saveBook(@ModelAttribute("book") book b) {
        bookService.addBook(b);
        return "redirect:/books";
    }

    @GetMapping("/edit-book/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        book b = bookService.getBookById(id);
        model.addAttribute("book", b);
        return "edit_book";
    }

    @PostMapping("/edit-book")
    public String updateBook(@ModelAttribute("book") book b) {
    bookService.updateBook(b.getId(), b);
    return "redirect:/books";
}

    @GetMapping("/delete-confirm/{id}")
    public String showDeleteConfirm(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "delete_book"; 
    }

    @PostMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}