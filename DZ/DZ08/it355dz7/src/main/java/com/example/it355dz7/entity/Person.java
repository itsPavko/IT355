package com.example.it355dz7.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    // constructors, getters, setters
}