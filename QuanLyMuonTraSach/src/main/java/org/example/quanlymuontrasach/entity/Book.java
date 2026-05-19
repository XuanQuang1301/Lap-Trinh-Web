package org.example.quanlymuontrasach.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Book")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String category;
    private Integer status;
}
