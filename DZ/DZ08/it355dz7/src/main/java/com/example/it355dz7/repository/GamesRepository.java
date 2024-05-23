package com.example.it355dz7.repository;

import com.example.it355dz7.entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamesRepository extends JpaRepository<Games, Long> {

    List<Games> findGamesByGamesYear(int year);
}
