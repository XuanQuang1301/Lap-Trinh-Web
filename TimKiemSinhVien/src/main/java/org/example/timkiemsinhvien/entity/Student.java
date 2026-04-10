package org.example.timkiemsinhvien.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Entity

@Data
public class Student {
    @Id
    private String id;
    private String name;
    private Date dob;
    private String department;
    private int selected;
}
