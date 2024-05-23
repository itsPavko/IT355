package com.example.it355dz7.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "noc_region")
public class NocRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "noc")
    private String noc;

    @Column(name = "region_name")
    private String regionName;

    // constructors, getters, setters
}
