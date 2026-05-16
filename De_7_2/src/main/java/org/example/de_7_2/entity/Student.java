package org.example.de_7_2.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "Student")
public class Student {
    @Id
    private String id;
    private String name;
    private LocalDate dob;
    private String department;
    private int approved;
}
