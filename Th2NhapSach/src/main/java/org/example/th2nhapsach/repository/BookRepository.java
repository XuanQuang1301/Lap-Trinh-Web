package org.example.th2nhapsach.repository;

import org.example.th2nhapsach.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
    Book findByBookCode(String bookCode);
}
