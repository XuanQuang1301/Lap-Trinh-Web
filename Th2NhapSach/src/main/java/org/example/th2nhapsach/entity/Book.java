package org.example.th2nhapsach.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Book")
@Data

public class Book {
    @Id
    private String bookCode;
    private String title;
    private String author;
    private String category;
}
