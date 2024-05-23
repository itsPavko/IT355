package com.example.it355dz7.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "games_year")
    private Integer gamesYear;

    @Column(name = "games_name")
    private String gamesName;

    @Column(name = "season")
    private String season;

    // constructors, getters, setters
}
