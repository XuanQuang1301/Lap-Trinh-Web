package org.example.de7nhapsinhvien.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data

public class Student {
    @Id
    private String id;
    private String name;
    private LocalDate dob;
    private String department;
    private Integer approved;
}
