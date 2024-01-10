package com.example.datatables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "offices")
class Address {

    @Id
    @GeneratedValue
    private int id;
    private String city;
    private String state;
    private String zip;
    private String street;
    private String country;
}
