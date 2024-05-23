package com.example.it355dz7.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medal")
public class Medal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medal_name")
    private String medalName;

    // constructors, getters, setters
}
