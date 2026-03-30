package com.example.baitap.controller;

import com.example.baitap.model.Book;
import com.example.baitap.service.BookService;
import com.example.baitap.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
public class BookViewController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "home";
    }

    @GetMapping("/books")
    public String listBooks(Model model, 
                            @RequestParam(name = "keyword", required = false) String keyword,
                            @RequestParam(name = "categoryId", required = false) Integer categoryId,
                            @RequestParam(name = "sort", defaultValue = "") String sort,
                            @RequestParam(name = "page", defaultValue = "0") int page) {
        Sort sortOrder = Sort.unsorted();
        if ("price_asc".equals(sort)) {
            sortOrder = Sort.by("price").ascending();
        } else if ("price_desc".equals(sort)) {
            sortOrder = Sort.by("price").descending();
        }

        Pageable pageable = PageRequest.of(page, 5, sortOrder);
        Page<Book> bookPage;
        
        if (keyword != null && !keyword.isEmpty()) {
            bookPage = bookService.searchBooks(keyword, pageable);
            model.addAttribute("keyword", keyword);
        } else if (categoryId != null && categoryId > 0) {
            bookPage = bookService.getBooksByCategory(categoryId, pageable);
            model.addAttribute("categoryId", categoryId);
        } else {
            bookPage = bookService.getAllBooks(pageable);
        }
        
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("sort", sort);
        return "book"; 
    }

    @GetMapping("/add-book")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add_book"; 
    }

    @PostMapping("/add-book")
    public String saveBook(@ModelAttribute("book") Book b) {
        bookService.addBook(b);
        return "redirect:/books";
    }

    @GetMapping("/edit-book/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Book b = bookService.getBookById(id);
        model.addAttribute("book", b);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit_book";
    }

    @PostMapping("/edit-book")
    public String updateBook(@ModelAttribute("book") Book b) {
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