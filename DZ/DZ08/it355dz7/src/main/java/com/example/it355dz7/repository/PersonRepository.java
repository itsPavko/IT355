package com.example.it355dz7.repository;

import com.example.it355dz7.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByHeightAndWeight(Integer height, Integer weight);
}
