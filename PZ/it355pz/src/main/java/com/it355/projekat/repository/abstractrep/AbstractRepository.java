package com.it355.projekat.repository.abstractrep;

import com.it355.projekat.beanannotation.Exclude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Exclude
public interface AbstractRepository<T> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
}

