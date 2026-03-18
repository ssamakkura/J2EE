package com.example.baitap.repository;

import com.example.baitap.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByCategoryId(int categoryId);
    List<Book> findByTitleContaining(String title);
}