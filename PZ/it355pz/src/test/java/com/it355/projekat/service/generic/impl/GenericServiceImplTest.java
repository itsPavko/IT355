package com.it355.projekat.service.generic.impl;

import com.it355.projekat.repository.abstractrep.AbstractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GenericServiceImplTest<T> {

    private GenericServiceImpl<T> genericService;

    @Mock
    private AbstractRepository<T> abstractRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        genericService = new GenericServiceImpl<>(abstractRepository);
    }

    @Test
    public void testFindAll() {
        List<T> objects = new ArrayList<>();
        when(abstractRepository.findAll()).thenReturn(objects);

        List<T> result = genericService.findAll();

        assertEquals(objects, result);
        verify(abstractRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        T object = mockObject();
        genericService.save(object);
        verify(abstractRepository, times(1)).save(Objects.requireNonNull(object));
    }

    @Test
    public void testUpdate() {
        T object = mockObject();
        genericService.update(object);
        verify(abstractRepository, times(1)).save(Objects.requireNonNull(object));
    }

    @Test
    public void testDelete() {
        Integer objectId = 1;
        genericService.delete(objectId);
        verify(abstractRepository, times(1)).deleteById(objectId);
    }

    @Test
    public void testFindById() {
        Integer objectId = 1;
        T object = mockObject();

        when(abstractRepository.findById(objectId)).thenReturn(Optional.ofNullable(object));
        T result = genericService.findById(objectId);

        assertEquals(object, result);
        verify(abstractRepository, times(1)).findById(objectId);
    }

    @Test
    public void testFindByIdNotFound() {
        Integer objectId = 1;
        when(abstractRepository.findById(objectId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> genericService.findById(objectId));
        verify(abstractRepository, times(1)).findById(objectId);
    }

    // Helper method to mock the object type
    private T mockObject() {
        return (T) mock(Object.class);
    }
}