package com.example.it355dz7.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "games_city")
public class GamesCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "games_id", referencedColumnName = "id")
    private Games games;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    // constructors, getters, setters
}
