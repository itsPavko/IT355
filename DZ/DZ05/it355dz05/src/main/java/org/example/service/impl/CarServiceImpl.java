package org.example.service.impl;

import org.example.entity.Car;
import org.example.service.CarService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {
    private final Map<Integer, Car> cars = new HashMap<>();

    public CarServiceImpl() {
        cars.put(1, new Car(1, "Fiat Punto", "Fiat", "Punto", 20.0));
        cars.put(2, new Car(2, "Ford Focus", "Ford", "Focus", 30.0));
        cars.put(3, new Car(3, "Volkswagen Golf", "Volkswagen", "Golf", 25.0));
    }

    @Override
    public Collection<Car> findAll() {
        return cars.values();
    }

    @Override
    public Car findById(int id) {
        return cars.get(id);
    }
}
