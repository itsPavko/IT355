package it355.dz6.dz6.controller;

import it355.dz6.dz6.entity.Proizvod;
import it355.dz6.dz6.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProizvodController {

    @Autowired
    private ProizvodRepository proizvodRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Proizvod> proizvodi = proizvodRepository.findAll();
        model.addAttribute("proizvodi", proizvodi);
        return "index";
    }

    @GetMapping("/dodaj/{id}")
    public String dodaj(@PathVariable Integer id, Model model) {
        Proizvod proizvod = proizvodRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        model.addAttribute("proizvod", proizvod);
        return "korpa";
    }

    @PostMapping("/placanje")
    public String placanje(@RequestParam String ime, @RequestParam String adresa, Model model) {
        model.addAttribute("ime", ime);
        model.addAttribute("adresa", adresa);

        return "success";
    }
}
