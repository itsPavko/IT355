package org.example.controller;

import org.example.entity.Car;
import org.example.entity.User;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/")
    public String carList(Model model) {

        model.addAttribute("cars", carService.findAll());
        return "cars";
    }

    @GetMapping("/rent/{id}")
    public String rentCar(@PathVariable Integer id, Model model) {
        Car car = carService.findById(id);

        model.addAttribute("car", car);
        return "rentCar";
    }

    @GetMapping("/approve/{id}")
    public String approveRent(@PathVariable Integer id,
                              @RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String phoneNumber,
                              Model model) {
        Car car = carService.findById(id);

        if (car.isRented()) {
            throw new IllegalArgumentException("Automobil " + car.getBrand() + " " + car.getModel() + " je već iznajmljen.");
        }

        car.setRented(true);

        User user = new User(firstName, lastName, phoneNumber);

        model.addAttribute("user", user);
        model.addAttribute("car", car);
        return "successRent";
    }
}
