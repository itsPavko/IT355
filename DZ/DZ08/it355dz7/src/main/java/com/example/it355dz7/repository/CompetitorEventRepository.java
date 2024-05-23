package com.example.it355dz7.repository;

import com.example.it355dz7.entity.CompetitorEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetitorEventRepository extends JpaRepository<CompetitorEvent, Long> {

    List<CompetitorEvent> findMedalistsByMedalIdAndEventId(Long medalId, Long eventId);

    int countCompetitorsByEventIdAndCompetitorId(Long eventId, Long competitorId);
}
