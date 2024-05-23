package com.example.it355dz7.repository;

import com.example.it355dz7.entity.GamesCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamesCityRepository extends JpaRepository<GamesCity, Long> {

    List<GamesCity> findAllByCityId(Long cityId);
}
