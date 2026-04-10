package org.example.test_de7.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
public class Student {
    @Id
    @NotBlank(message = "ID Khong duoc de trong")
    private String id;
    @NotBlank(message = "Ten khong duoc de trong")
    private String name;

    private LocalDate dod;
    @NotBlank(message = "Phòng ban không được để tronsg")
    private String department;
    private int approved = 0;

}
