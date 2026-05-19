package org.example.quanlymuontrasach.repository;

import org.example.quanlymuontrasach.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    @Query("SELECT b FROM Book b  WHERE :category = '' OR b.category LIKE %:category%"
    )
    List<Book> searchByCategory(String category);

    @Override
    boolean existsById(String id);
}
