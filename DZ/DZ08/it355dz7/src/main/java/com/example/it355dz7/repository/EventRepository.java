package com.example.it355dz7.repository;

import com.example.it355dz7.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
