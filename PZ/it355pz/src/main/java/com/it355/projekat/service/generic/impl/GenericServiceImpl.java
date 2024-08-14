package com.it355.projekat.service.generic.impl;

import com.it355.projekat.repository.abstractrep.AbstractRepository;
import com.it355.projekat.service.generic.GenericService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
public class GenericServiceImpl<T> implements GenericService<T> {

    protected final AbstractRepository<T> abstractRepository;

    @Override
    public List<T> findAll() {
        return abstractRepository.findAll();
    }

    @Override
    public T save(T object) {
        return abstractRepository.save(object);
    }

    @Override
    public T update(T object) {
        return abstractRepository.save(object);
    }

    @Override
    public void delete(Integer id) {
        abstractRepository.deleteById(id);
    }

    @Override
    public T findById(Integer id) {
        return abstractRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
