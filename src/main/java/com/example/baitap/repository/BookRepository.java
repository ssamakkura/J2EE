package com.example.baitap.repository;

import com.example.baitap.model.book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<book, Integer> {
    List<book> findByCategoryId(int categoryId);
    List<book> findByTitleContaining(String title);
}