package org.example.quanlyungvien.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Candidate")
public class Candidate {
    @Id
    private String id;
    private String name;
    private String position;
    private Integer expYear;
    private Integer status;
}
