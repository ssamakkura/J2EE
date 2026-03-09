package com.example.baitap.controller;

import com.example.baitap.model.Category;
import com.example.baitap.service.categoryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class categorycontroller {

    @Autowired
    private categoryservice categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/add")
    public String addCategory(@RequestBody Category c) {
        categoryService.addCategory(c);
        return "Category added";
    }

    @PutMapping("/{id}")
    public String updateCategory(@PathVariable int id, @RequestBody Category c) {
        categoryService.updateCategory(id, c);
        return "Category updated";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "Category deleted";
    }
}