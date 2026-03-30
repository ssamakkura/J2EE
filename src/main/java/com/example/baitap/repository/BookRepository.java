package com.example.baitap.repository;

import com.example.baitap.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Page<Book> findByCategoryId(int categoryId, Pageable pageable);
    Page<Book> findByTitleContaining(String title, Pageable pageable);
}