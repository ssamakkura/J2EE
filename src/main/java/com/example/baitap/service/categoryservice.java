package com.example.baitap.service;

import com.example.baitap.model.Category;
import com.example.baitap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(int id, Category updateCategory) {
        if (categoryRepository.existsById(id)) {
            updateCategory.setId(id);
            categoryRepository.save(updateCategory);
        }
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}