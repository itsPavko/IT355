package it355.dz6.dz6.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "proizvod")
public class Proizvod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String naziv;
    private String cena;

    public Proizvod() {
    }

    public Proizvod(Integer id, String naziv, String cena) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }
}
