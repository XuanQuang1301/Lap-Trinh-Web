package org.example.de8duyetsinhvien.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Student")
public class Student {
    @Id
    private String id;
    private String name;
    private LocalDate dob;
    private String department;
    private Integer approved;
}
