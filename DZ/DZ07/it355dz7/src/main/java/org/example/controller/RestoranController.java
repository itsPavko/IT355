package org.example.controller;

import org.example.entity.Restoran;
import org.example.repository.RestoranRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Controller
public class RestoranController {

    private final RestoranRepository restoranRepository;

    public RestoranController(RestoranRepository restoranRepository) {
        this.restoranRepository = restoranRepository;
    }

    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("restorani", restoranRepository.findAll());
        return "index";
    }

    @GetMapping("/napraviRestoran")
    public String create(Model model) {
        model.addAttribute("restoran", new Restoran());
        return "napraviRestoran";
    }

    @PostMapping("/sacuvajRestoran")
    public String save(@Validated Restoran restoran, BindingResult result) {
        if (result.hasErrors()) {
            return "napraviRestoran";
        }
        restoranRepository.save(restoran);
        return "redirect:/";
    }

    @GetMapping("/azuriranjeRestorana")
    public String edit(@RequestParam Long id, Model model) {
        Optional<Restoran> restoran = restoranRepository.findById(id);

        if (restoran.isPresent()) {
            model.addAttribute("restoran", restoran.get());
            return "napraviRestoran";
        } else {
            return "redirect:/";
        }
    }

    @DeleteMapping("/brisanjeRestorana")
    public String delete(@RequestParam Long id) {
        restoranRepository.deleteById(id);
        return "redirect:/";
    }
}
