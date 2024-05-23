package com.example.it355dz7.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "games_competitor")
public class GamesCompetitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "games_id", referencedColumnName = "id")
    private Games games;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "age")
    private Integer age;

    // constructors, getters, setters
}
