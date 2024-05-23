package org.example.service;

import org.example.entity.Car;

import java.util.Collection;

public interface CarService {
    Collection<Car> findAll();

    Car findById(int id);
}
