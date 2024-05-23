package org.example.controller;

import org.example.entity.Jelo;
import org.example.repository.JeloRepository;
import org.example.repository.RestoranRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Optional;

@Controller
public class JeloController {
    private final JeloRepository jeloRepository;
    private final RestoranRepository restoranRepository;

    public JeloController(JeloRepository jeloRepository, RestoranRepository restoranRepository) {
        this.jeloRepository = jeloRepository;
        this.restoranRepository = restoranRepository;
    }

    @GetMapping("/jela")
    public String getAll(Model model) {
        model.addAttribute("jela", jeloRepository.findAll());
        return "jelo";
    }

    @GetMapping("/napraviJelo")
    public String create(Model model) {
        model.addAttribute("jelo", new Jelo());
        model.addAttribute("restorani", restoranRepository.findAll());
        return "napraviJelo";
    }

    @PostMapping("/sacuvajJelo")
    public String save(@Validated Jelo jelo, BindingResult result) {
        if (result.hasErrors()) {
            return "napraviJelo";
        }
        jeloRepository.save(jelo);
        return "redirect:/jela";
    }

    @GetMapping("/izmeniJelo")
    public String edit(@RequestParam Long id, Model model) {
        Optional<Jelo> jelo = jeloRepository.findById(id);

        if (jelo.isPresent()) {
            model.addAttribute("jelo", jelo.get());
            return "napraviJelo";
        } else {
            return "redirect:/jela";
        }
    }

    @GetMapping("/obrisiJelo")
    public String delete(@RequestParam Long id) {
        jeloRepository.deleteById(id);
        return "redirect:/jela";
    }
}
