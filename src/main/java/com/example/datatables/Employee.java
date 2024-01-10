package com.example.datatables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private String gender;
    private double salary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_office")
    private Address address;
}
