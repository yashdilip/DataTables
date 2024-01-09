package com.example.datatables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_employee")
public class Employee {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private String gender;
    private double salary;
}
