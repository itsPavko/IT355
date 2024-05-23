package com.example.it355dz7.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "competitor_event")
public class CompetitorEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "competitor_id")
    private GamesCompetitor competitor;

    @ManyToOne
    @JoinColumn(name = "medal_id")
    private Medal medal;

    // getteri i setteri
}
