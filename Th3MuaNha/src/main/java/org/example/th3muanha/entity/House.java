package org.example.th3muanha.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "House")
@Data
public class House {
    @Id
    private String id;
    private String address;
    private Integer area;
    private String type;
    private Integer sold;
}
