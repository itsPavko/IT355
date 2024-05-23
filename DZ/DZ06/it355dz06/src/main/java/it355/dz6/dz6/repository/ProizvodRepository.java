package it355.dz6.dz6.repository;

import it355.dz6.dz6.entity.Proizvod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod, Integer> {
}
