package com.example.it355dz7.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    //private Sport sport;

    @Column(name = "event_name")
    private String eventName;

    // constructors, getters, setters
}